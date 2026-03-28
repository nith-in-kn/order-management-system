package com.ordermanagement.orderservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = "items")
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
        orphanRemoval = true,
        fetch = FetchType.EAGER
    )
    @JsonManagedReference
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
