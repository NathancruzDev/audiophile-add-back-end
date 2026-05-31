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
@Table(name = "users_entity")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String emailAddress;
    private String password;
    private String phoneNumber;

    private String address;
    private String zipCode;
    private String city;
    private String country;

    private ArrayList<String> paymentMethods;
    private ArrayList<OrderStatus> lastOrders;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    public UserEntity() {
    }

    public UserEntity(Integer id, String name, String emailAddress, String phoneNumber, String address, String password , String zipCode, String city, String country, ArrayList<String> paymentMethods, ArrayList<OrderStatus> lastOrders, UserRole role) {
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
        this.role = role;
    }

    public UserEntity(UserCreateDto userCreateDto) {
        // Mapeie os outros campos do seu DTO aqui, por exemplo:
        // this.name = userCreateDto.name();
        // this.emailAddress = userCreateDto.emailAdress();
        // this.password = userCreateDto.password(); // Lembre-se de encodar a senha no service depois!

        // Define que todo mundo nasce como USER
        this.role = UserRole.USER;
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

    public ArrayList<String> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(ArrayList<String> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public ArrayList<OrderStatus> getLastOrders() {
        return lastOrders;
    }

    public void setLastOrders(ArrayList<OrderStatus> lastOrders) {
        this.lastOrders = lastOrders;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == null) {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.role.name()));
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return emailAddress; // Retornando o email como username para o Spring Security
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