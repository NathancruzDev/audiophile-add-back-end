package com.example.back_end.model.entity;

import com.example.back_end.model.dto.UserCreateDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.query.Order;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users_entity")
public class UserEntity implements UserDetails {

    Integer id;
    String name;
    String emailAdress;
    String password;
    String phoneNumber;

    String adress;

    String zipCode;
    String city;
    String country;

    ArrayList<String> paymentMethods;
    ArrayList<OrderStatus>  lastOrders;
    ArrayList<ProductEntity> cartItens;



    public UserEntity() {
    }

    public UserEntity(Integer id, String name, String emailAdress, String phoneNumber, String adress,String password ,String zipCode, String city, String country, ArrayList<String> paymentMethods, ArrayList<OrderStatus> lastOrders, ArrayList<ProductEntity> cartItens) {
        this.id = id;
        this.name = name;
        this.emailAdress = emailAdress;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.adress = adress;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
        this.paymentMethods = paymentMethods;
        this.lastOrders = lastOrders;
        this.cartItens = cartItens;
    }

    public UserEntity(UserCreateDto userCreateDto) {
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

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
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

    public ArrayList<ProductEntity> getCartItens() {
        return cartItens;
    }

    public void setCartItens(ArrayList<ProductEntity> cartItens) {
        this.cartItens = cartItens;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        //UserDetails.super.isAccountNonExpired()
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        //UserDetails.super.isAccountNonLocked()
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //UserDetails.super.isAccountNonExpired()
        return true;
    }

    @Override
    public boolean isEnabled() {
        //UserDetails.super.isEnabled();
        return true;
    }
}
