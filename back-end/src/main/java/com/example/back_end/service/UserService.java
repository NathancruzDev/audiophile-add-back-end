package com.example.back_end.service;

import com.example.back_end.config.security.TokenConfig;
import com.example.back_end.model.dto.OrderPendingDto;
import com.example.back_end.model.dto.user.*;
import com.example.back_end.model.entity.UserEntity;
import com.example.back_end.repository.AdressLockupClient;
import com.example.back_end.repository.ProductRepository;
import com.example.back_end.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private TokenConfig tokenConfig;
    private ProductRepository productRepository;
    private PasswordEncoder passwordEncoder;
    private PurchasedService purchasedService;
    private ViaCepLockupService viaCepLockupService;
    private final AdressLockupClient adressLockupClient;

    public UserService(UserRepository userRepository, AuthenticationManager authenticationManager, TokenConfig tokenConfig, ProductRepository productRepository, PurchasedService purchasedService, ViaCepLockupService viaCepLockupService, AdressLockupClient adressLockupClient) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.tokenConfig = tokenConfig;
        this.productRepository = productRepository;
        this.purchasedService = purchasedService;
        this.viaCepLockupService = viaCepLockupService;
        this.adressLockupClient = adressLockupClient;
    }

    @Transactional
    public UserCreateDto createUser(@Valid UserCreateDto userCreateDto) {
        if (userRepository.existsByEmailAddress(userCreateDto.emailAdress())) {
            throw new RuntimeException("Email já cadastrado");
        }
        ViaCepDto viaCepDto = adressLockupClient.findAdressByCep(userCreateDto.zipCode()).orElseThrow(()->new IllegalArgumentException("Adress general error."));
        UserEntity userEntity = new UserEntity(userCreateDto,viaCepDto);
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

    @Transactional
    public ResponseEntity<UserLoginDto> login(UserLoginDto userLogin) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(userLogin.emailAdress(), userLogin.password());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        UserEntity user = (UserEntity) authentication.getPrincipal();
        String tokenJWT = tokenConfig.generateToken(user);
        return ResponseEntity.ok(new UserLoginDto(user.getEmailAddress(), tokenJWT));
    }

    @Transactional
    public void updateUser(UserUpdateDto userUpdateDto){
        UserEntity userEntity=userRepository.findByEmailAddress(userUpdateDto.emailAdress()).orElseThrow(() -> new RuntimeException("This user don't exists."));
            if(!userEntity.getEmailAddress().equals(userUpdateDto.emailAdress())){
                //troca de email tem que ser tratada mais séria.
                throw new RuntimeException("");
            }

        ViaCepDto viaCepDto=adressLockupClient.findAdressByCep(userUpdateDto.zipCode()).orElseThrow(()-> new RuntimeException("Adress general error."));

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
    public ResponseEntity<List<OrderPendingDto>> getUserRequests(UserDto userDto){
        UserEntity userEntity=userRepository.findById(userDto.id()).orElseThrow(()-> new RuntimeException("This user dont exists."));
        List<OrderPendingDto> list=purchasedService.listAllByUser(userDto.id());
        return ResponseEntity.ok().body(list);
    }

    @Transactional
    public ResponseEntity<List<OrderPendingDto>> getMyUserRequests(UserEntity loggedUser){
        List<OrderPendingDto> list = purchasedService.listAllByUser(loggedUser.getId());
        return ResponseEntity.ok().body(list);
    }

}

