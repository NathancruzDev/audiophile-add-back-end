package com.example.back_end.model.dto;

import com.example.back_end.model.entity.FormPayment;

import java.util.Date;

public record OrderPendingDto(
        Integer user_id,
        Integer purchased_id,
        String purchasedHashCode,
        Double momentValue,
        Date createAt,
        FormPayment formPayment,
        Boolean statusPayment
){
}
