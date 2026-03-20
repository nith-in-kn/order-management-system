package com.ordermanagement.orderservice.repository;

import com.ordermanagement.orderservice.Models.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
}
