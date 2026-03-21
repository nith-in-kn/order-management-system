package com.ordermanagement.orderservice.Models.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemResponse {

    private String id;

    private String productName;

    private BigDecimal unitPrice;

    private Integer quantity;

    private BigDecimal subTotal;
}
