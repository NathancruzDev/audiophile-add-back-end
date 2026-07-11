package com.example.back_end.model.entity;

import com.example.back_end.model.dto.user.UserCreateDto;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tb_users")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(unique = true)
    private String emailAddress;

    private String password;
    private String phoneNumber;

    private String address;
    private String zipCode;
    private String city;
    private String country;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "user_payment_methods",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "payment_method")
    private List<String> paymentMethods = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "USER_LAST_ORDERS",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private List<OrderStatusEnum> lastOrders = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRole user_role;


    public UserEntity() {
    }

    // Construtor completo
    public UserEntity(Integer id, String name, String emailAddress, String phoneNumber, String address, String password, String zipCode, String city, String country, List<String> paymentMethods, List<OrderStatusEnum> lastOrders, UserRole role) {
        this.id = id;
        this.name = name;
        this.emailAddress = emailAddress;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
        this.paymentMethods = paymentMethods;
        this.lastOrders = lastOrders;
        this.user_role = role;
    }

    // Construtor via DTO
    public UserEntity(UserCreateDto userCreateDto) {
        this.name = userCreateDto.name();
        this.emailAddress = userCreateDto.emailAdress(); // Mantido o mapeamento do seu DTO
        this.password = userCreateDto.password();
        this.phoneNumber = userCreateDto.phoneNumber();
        this.address = userCreateDto.adress();           // Mantido o mapeamento do seu DTO
        this.zipCode = userCreateDto.zipCode();
        this.city = userCreateDto.City();
        this.country = userCreateDto.Country();
        this.user_role = UserRole.USER;
        this.paymentMethods = new ArrayList<>();
        this.lastOrders = new ArrayList<>();
    }

    // --- GETTERS E SETTERS (Mantidos idênticos para não quebrar seu código externo) ---

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<String> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<String> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public List<OrderStatusEnum> getLastOrders() {
        return lastOrders;
    }

    public void setLastOrders(List<OrderStatusEnum> lastOrders) {
        this.lastOrders = lastOrders;
    }

    public UserRole getUser_role() {
        return user_role;
    }

    public void setUser_role(UserRole user_role) {
        this.user_role = user_role;
    }

    // --- MÉTODOS DO USERDETAILS (SPRING SECURITY) ---

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.user_role == null) {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.user_role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return emailAddress;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}