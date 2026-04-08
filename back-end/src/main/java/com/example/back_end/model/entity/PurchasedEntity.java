package com.example.back_end.model.entity;

import com.example.back_end.model.entity.PaymentMethods.FormPayment;

import java.util.Date;

public class PurchasedEntity {

    Integer id;
    Integer user_id;
    String purchasedHashCode;
    Double momentValue;
    Date createAt;
    FormPayment formPayment;

    public PurchasedEntity(Integer id, Integer user_id, String purchasedHashCode, Double momentValue, Date createAt, FormPayment formPayment) {
        this.id = id;
        this.user_id = user_id;
        this.purchasedHashCode = purchasedHashCode;
        this.momentValue = momentValue;
        this.createAt = createAt;
        this.formPayment = formPayment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
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
    //acabar essa entidade, criar um servico para manipular as compras e gerar as tabelas de compras, e criar uma interface por enquanto intermediaria de pagamentos, para
    //depois fazer a implementacao do sistema de pagamento
}
