package com.example.back_end.model.entity;

import com.example.back_end.model.dto.user.UserCreateDto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
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

    // --- CAMPOS DE ENDEREÇO MAPEADOS PARA O VIACEP ---
    private String zipCode;
    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;

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


    public UserEntity(@Valid UserCreateDto userCreateDto) {
    }

    public UserEntity(Integer id, String name, String emailAddress, String password, String phoneNumber, String zipCode, String street, String number, String complement, String neighborhood, String city, String state, List<String> paymentMethods, List<OrderStatusEnum> lastOrders, UserRole user_role) {
        this.id = id;
        this.name = name;
        this.emailAddress = emailAddress;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.zipCode = zipCode;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.paymentMethods = paymentMethods;
        this.lastOrders = lastOrders;
        this.user_role = user_role;
    }

    public UserEntity(String name, String emailAddress, String password, String zipCode, String street, String number, String complement, String neighborhood, String city, String state) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.password = password;
        this.zipCode = zipCode;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
    }

    public UserEntity(@Valid List<UserCreateDto> userCreateDto) {
    }

    public UserEntity() {

    }


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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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