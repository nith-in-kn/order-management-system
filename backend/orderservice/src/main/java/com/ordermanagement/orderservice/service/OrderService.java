package com.ordermanagement.orderservice.service;

import com.ordermanagement.orderservice.entity.Order;

import java.util.List;

public interface OrderService {
    Order saveOrder(Order order);
    Order getOrderById(int orderId);
    List<Order> getAll();
}
