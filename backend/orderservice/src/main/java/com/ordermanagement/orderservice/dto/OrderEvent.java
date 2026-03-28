package com.ordermanagement.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent {
    private int id;
    private int customerId;
    private int paymentId;
    private LocalDateTime orderDate;
    private BigDecimal totalAmount;
    private List<OrderItemEvent> items;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItemEvent {
        private int productId;
        private String name;
        private BigDecimal unitPrice;
        private int quantity;
        private BigDecimal subTotal;
    }
}