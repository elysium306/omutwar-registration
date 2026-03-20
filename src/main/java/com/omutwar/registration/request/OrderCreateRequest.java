package com.omutwar.registration.request;

import java.math.BigDecimal;

public class OrderCreateRequest {

    public long userId;
    public BigDecimal totalAmount;
    public String status;
}