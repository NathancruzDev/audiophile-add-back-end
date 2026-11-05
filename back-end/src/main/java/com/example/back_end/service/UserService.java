package com.example.back_end.service;

import com.example.back_end.config.security.TokenConfig;
import com.example.back_end.model.dto.OrderPendingDto;
import com.example.back_end.model.dto.user.*;
import com.example.back_end.model.entity.UserEntity;
import com.example.back_end.model.entity.UserRole;
import com.example.back_end.repository.AdressLockupClient;
import com.example.back_end.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenConfig tokenConfig;
    private final PasswordEncoder passwordEncoder;
    private final PurchasedService purchasedService;
    private final AdressLockupClient adressLockupClient;

    public UserService(UserRepository userRepository, AuthenticationManager authenticationManager, TokenConfig tokenConfig, PasswordEncoder passwordEncoder, PurchasedService purchasedService, AdressLockupClient adressLockupClient) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.tokenConfig = tokenConfig;
        this.passwordEncoder = passwordEncoder;
        this.purchasedService = purchasedService;
        this.adressLockupClient = adressLockupClient;
    }

    @Transactional
    public UserCreateDto createUser(@Valid UserCreateDto userCreateDto) {
        if (userRepository.existsByEmailAddress(userCreateDto.emailAdress())) {
            throw new RuntimeException("Email já cadastrado");
        }
        ViaCepDto viaCepDto = adressLockupClient.findAdressByCep(userCreateDto.zipCode())
                .orElseThrow(() -> new IllegalArgumentException("Address general error."));

        String encryptedPassword = passwordEncoder.encode(userCreateDto.password());
        UserEntity userEntity = new UserEntity(userCreateDto, viaCepDto);
        userEntity.setPassword(encryptedPassword);
        userRepository.save(userEntity);
        return userCreateDto;
    }

    @Transactional
    public List<UserEntity> createManyUsers(List<@Valid UserCreateDto> userCreateDtos) {
        List<UserEntity> userEntitiesList = userCreateDtos.stream()
                .map(dto -> {
                    UserEntity user = new UserEntity();
                    user.setName(dto.name());
                    user.setEmailAddress(dto.emailAdress());
                    user.setPassword(passwordEncoder.encode(dto.password()));
                    user.setPhoneNumber(dto.phoneNumber());
                    user.setZipCode(dto.zipCode());
                    user.setStreet(dto.street());
                    user.setNeighborhood(dto.neighborhood());
                    user.setCity(dto.city());
                    user.setState(dto.state());
                    return user;
                })
                .collect(Collectors.toList());

        return userRepository.saveAll(userEntitiesList);
    }

    public List<GetUsersDto> getAllUsers(){
        return userRepository.findAll().stream().map(
                userEntity -> new GetUsersDto(
                        userEntity.getId(),
                        userEntity.getName(),
                        userEntity.getEmailAddress(),
                        userEntity.getPhoneNumber()
                )
        ).collect(Collectors.toList());
    }

    public UserDto getUserById(Integer id){
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        return new UserDto(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getEmailAddress(),
                userEntity.getPhoneNumber(),
                userEntity.getZipCode(),
                userEntity.getStreet(),
                userEntity.getNumber(),
                userEntity.getComplement(),
                userEntity.getNeighborhood(),
                userEntity.getCity(),
                userEntity.getState(),
                userEntity.getPaymentMethods(),
                userEntity.getLastOrders()
        );
    }

    public UserLoginDto login(UserLoginDto userLogin) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(userLogin.emailAdress(), userLogin.password());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        UserEntity user = (UserEntity) authentication.getPrincipal();
        String tokenJWT = tokenConfig.generateToken(user);
        return new UserLoginDto(user.getEmailAddress(), tokenJWT);
    }

    @Transactional
    public void updateUser(UserUpdateDto userUpdateDto){
        UserEntity userEntity = userRepository.findByEmailAddress(userUpdateDto.emailAdress())
                .orElseThrow(() -> new RuntimeException("This user doesn't exist."));

        ViaCepDto viaCepDto = adressLockupClient.findAdressByCep(userUpdateDto.zipCode())
                .orElseThrow(() -> new RuntimeException("Address general error."));

        userEntity.setName(userUpdateDto.name());
        userEntity.setPhoneNumber(userUpdateDto.phoneNumber());
        userEntity.setZipCode(viaCepDto.cep());
        userEntity.setStreet(viaCepDto.logradouro());
        userEntity.setNumber(userUpdateDto.number());
        userEntity.setComplement(userUpdateDto.complement());
        userEntity.setNeighborhood(viaCepDto.bairro());
        userEntity.setCity(viaCepDto.localidade());
        userEntity.setState(viaCepDto.uf());

        userRepository.save(userEntity);
    }

    @Transactional
    public void updateUserById(Integer id, @NonNull UserUpdateDto userUpdateDto){
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("This user doesn't exist."));

        ViaCepDto viaCepDto = adressLockupClient.findAdressByCep(userUpdateDto.zipCode())
                .orElseThrow(() -> new RuntimeException("Address general error."));

        userEntity.setName(userUpdateDto.name());
        userEntity.setPhoneNumber(userUpdateDto.phoneNumber());
        userEntity.setZipCode(viaCepDto.cep());
        userEntity.setStreet(viaCepDto.logradouro());
        userEntity.setNumber(userUpdateDto.number());
        userEntity.setComplement(userUpdateDto.complement());
        userEntity.setNeighborhood(viaCepDto.bairro());
        userEntity.setCity(viaCepDto.localidade());
        userEntity.setState(viaCepDto.uf());

        userRepository.save(userEntity);
    }

    public List<OrderPendingDto> getUserRequests(Integer userId){
        userRepository.findById(userId).orElseThrow(() -> new RuntimeException("This user doesn't exist."));
        return purchasedService.listAllByUser(userId);
    }

    public List<OrderPendingDto> getMyUserRequests(UserEntity loggedUser){
        return purchasedService.listAllByUser(loggedUser.getId());
    }

    @Transactional
    public void updateUserRole(String id, String newRole){
        UserEntity userEntity = userRepository.findById(Integer.valueOf(id))
                .orElseThrow(() -> new RuntimeException("User not exist"));
        userEntity.setUser_role(UserRole.valueOf(newRole));
        userRepository.save(userEntity);
    }

    public Boolean isUserLogged(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated() &&
                !(authentication.getPrincipal() instanceof String && authentication.getPrincipal().equals("anonymousUser"));
    }
}