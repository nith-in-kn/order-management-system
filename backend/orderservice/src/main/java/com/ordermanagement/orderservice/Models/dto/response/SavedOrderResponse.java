package com.ordermanagement.orderservice.Models.dto.response;

import com.ordermanagement.orderservice.shared.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SavedOrderResponse {

    private String id;

    private String customerId;

    private OrderStatus orderStatus;

    private BigDecimal totalAmount;

    private LocalDateTime createdAt;
}
