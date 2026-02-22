package com.example.back_end.model.entity.PaymentMethods;

import java.time.LocalDateTime;
import java.util.UUID;

public class Payment {
    private UUID id;
    private UUID orderStatusId;
    private PaymentState paymentState;
    private FormPayment formPayment;
    private LocalDateTime dateCreate;

}
