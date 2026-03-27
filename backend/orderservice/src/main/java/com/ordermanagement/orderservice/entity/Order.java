package com.ordermanagement.orderservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity @Table(name = "orders")
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;

    private int customer_id;

    private int payment_id;

    @OneToMany(
        mappedBy = "order",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<OrderItem> items = new ArrayList<>();

    public void setItems(List<OrderItem> items){
        this.items.addAll(items);
        items.stream().forEach(i -> i.setOrder(this)); // without FK null
    }

    public void addItem(OrderItem item){
        this.items.add(item);
        item.setOrder(this);
    }

}
