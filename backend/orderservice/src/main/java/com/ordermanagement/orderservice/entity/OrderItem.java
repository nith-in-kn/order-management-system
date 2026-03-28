package com.ordermanagement.orderservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString(exclude = "order")
@Entity @Table(name = "order_items")
public class OrderItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;

    private int productId;

    private String name;

    private BigDecimal unitPrice;

    private int quantity;

    private BigDecimal subTotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Order order;


}
