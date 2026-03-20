package com.ordermanagement.orderservice.Models.dto.response;

import com.ordermanagement.orderservice.Models.Entity.Order;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
