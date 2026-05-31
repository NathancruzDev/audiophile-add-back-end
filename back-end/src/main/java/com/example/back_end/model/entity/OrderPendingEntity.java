package com.example.back_end.model.entity;

import com.example.back_end.model.entity.FormPayment;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_pending")
public class OrderPendingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    Integer userId;
    Integer purchasedId;
    String purchasedHashCode;
    Double momentValue;
    LocalDateTime createAt;
    FormPayment formPayment;
    StateStatus stateStatus;


    public OrderPendingEntity() {
    }

    public OrderPendingEntity(Integer id, Integer userId, Integer purchasedId, String purchasedHashCode, Double momentValue, LocalDateTime createAt, FormPayment formPayment, StateStatus stateStatus) {
        this.id=id;
        this.userId= userId;
        this.purchasedId = purchasedId;
        this.purchasedHashCode = purchasedHashCode;
        this.momentValue = momentValue;
        this.createAt = createAt;
        this.formPayment = formPayment;
        this.stateStatus = stateStatus;
    }

    public OrderPendingEntity(Integer userId, Integer purchasedId, String purchasedHashCode, Double momentValue, LocalDateTime createAt, FormPayment formPayment, StateStatus stateStatus) {
        this.userId = userId;
        this.purchasedId = purchasedId;
        this.purchasedHashCode = purchasedHashCode;
        this.momentValue = momentValue;
        this.createAt = createAt;
        this.formPayment = formPayment;
        this.stateStatus = stateStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StateStatus getStateStatus() {
        return stateStatus;
    }

    public void setStateStatus(StateStatus stateStatus) {
        this.stateStatus = stateStatus;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer user_id) {
        this.userId = userId;
    }

    public Integer getPurchasedId() {
        return purchasedId;
    }

    public void setPurchasedId(Integer purchasedId) {
        this.purchasedId = purchasedId;
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
