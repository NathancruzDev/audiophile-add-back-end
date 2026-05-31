package com.example.back_end.model.entity;

import com.example.back_end.model.entity.FormPayment;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "purchased")
public class PurchasedEntity {

    @Id
    Integer id;
    Integer userId;
    String purchasedHashCode;
    Double momentValue;
    Date createAt;
    FormPayment formPayment;
    Boolean aproved;

    public PurchasedEntity(Integer id, Integer userId, String purchasedHashCode, Double momentValue, Date createAt, FormPayment formPayment,Boolean aproved) {
        this.id = id;
        this.userId = userId;
        this.purchasedHashCode = purchasedHashCode;
        this.momentValue = momentValue;
        this.createAt = createAt;
        this.formPayment = formPayment;
        this.aproved = aproved;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserIdd(Integer userId) {
        this.userId = userId;
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

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public FormPayment getFormPayment() {
        return formPayment;
    }

    public void setFormPayment(FormPayment formPayment) {
        this.formPayment = formPayment;
    }

    public Boolean getAproved() {
        return aproved;
    }

    public void setAproved(Boolean aproved) {
        this.aproved = aproved;
    }

}
