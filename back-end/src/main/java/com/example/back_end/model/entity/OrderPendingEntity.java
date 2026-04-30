package com.example.back_end.model.entity;

import com.example.back_end.model.entity.FormPayment;

import java.time.LocalDateTime;

public class OrderPendingEntity {

    Integer user_id;
    Integer purchased_id;
    String purchasedHashCode;
    Double momentValue;
    LocalDateTime createAt;
    FormPayment formPayment;
    StateStatus stateStatus;

    public OrderPendingEntity(Integer user_id, Integer purchased_id, String purchasedHashCode, Double momentValue, LocalDateTime createAt, FormPayment formPayment, StateStatus stateStatus) {
        this.user_id = user_id;
        this.purchased_id = purchased_id;
        this.purchasedHashCode = purchasedHashCode;
        this.momentValue = momentValue;
        this.createAt = createAt;
        this.formPayment = formPayment;
        this.stateStatus = stateStatus;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getPurchased_id() {
        return purchased_id;
    }

    public void setPurchased_id(Integer purchased_id) {
        this.purchased_id = purchased_id;
    }

    public String getPurchasedHashCode() {
        return purchasedHashCode;
    }

    public void setPurchasedHashCode(String purchasedHashCode) {
        this.purchasedHashCode = purchasedHashCode;
    }

    public Double getMomentValue() {
        return momentValue;
    }

    public void setMomentValue(Double momentValue) {
        this.momentValue = momentValue;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public FormPayment getFormPayment() {
        return formPayment;
    }

    public void setFormPayment(FormPayment formPayment) {
        this.formPayment = formPayment;
    }

    public StateStatus getStatus() {
        return stateStatus;
    }

    public void setStatus(StateStatus stateStatus) {
        this.stateStatus = stateStatus;
    }
}
