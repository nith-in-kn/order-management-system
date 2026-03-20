package com.ordermanagement.orderservice.Models.dto.response;

import com.ordermanagement.orderservice.shared.enums.OrderStatus;
import lombok.Data;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class GetOrderResponse {

    private String id;

    private String customerId;

    private String customerName;

    private String deliveryAddress;

    private List<OrderItemResponse> items;

    private OrderStatus orderStatus;

    private String paymentId;

    private BigDecimal totalAmount;

    private LocalDateTime createdAt;

}
