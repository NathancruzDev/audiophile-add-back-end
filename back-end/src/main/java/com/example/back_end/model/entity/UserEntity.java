package com.example.back_end.model.entity;

import java.util.ArrayList;

public class UserEntity {

    Integer id;
    String name;
    String emailAdress;
    String phoneNUmber;

    String adress;

    String zipCode;
    String City;
    String Country;

    String paymentMethod;
    ArrayList<String>  lastOrders;
    ArrayList<ProductEntity> cartItens;

    public UserEntity(Integer id, String name, String emailAdress, String phoneNUmber, String adress, String zipCode, String city, String country, String paymentMethod, ArrayList<String> lastOrders, ArrayList<ProductEntity> cartItens) {
        this.id = id;
        this.name = name;
        this.emailAdress = emailAdress;
        this.phoneNUmber = phoneNUmber;
        this.adress = adress;
        this.zipCode = zipCode;
        City = city;
        Country = country;
        this.paymentMethod = paymentMethod;
        this.lastOrders = lastOrders;
        this.cartItens = cartItens;
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

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public String getPhoneNUmber() {
        return phoneNUmber;
    }

    public void setPhoneNUmber(String phoneNUmber) {
        this.phoneNUmber = phoneNUmber;
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
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public ArrayList<String> getLastOrders() {
        return lastOrders;
    }

    public void setLastOrders(ArrayList<String> lastOrders) {
        this.lastOrders = lastOrders;
    }

    public ArrayList<ProductEntity> getCartItens() {
        return cartItens;
    }

    public void setCartItens(ArrayList<ProductEntity> cartItens) {
        this.cartItens = cartItens;
    }
}
