package com.example.back_end.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class OrderStatus {

    UserEntity userEntity;
    UUID id;
    BigDecimal values;
    ArrayList<ProductEntity> cartProducts;
    LocalDateTime createDate;
}
