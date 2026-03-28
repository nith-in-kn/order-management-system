package com.ordermanagement.orderservice.service.impl;

import com.ordermanagement.orderservice.entity.Order;
import com.ordermanagement.orderservice.repository.OrderRepository;
import com.ordermanagement.orderservice.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service @AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private KafkaTemplate<String, Order> kafkaTemplate;

    @Transactional
    public Order saveOrder(Order order){
        log.debug("order service: saveOrder, order: {}", order);

        Order savedOrder = orderRepository.save(order);

        kafkaTemplate
                .send("orders", String.valueOf(savedOrder.getId()), savedOrder)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        log.error("Failed to publish order to Kafka: ", ex);
                    } else {
                        log.debug("Successfully published order to Kafka topic, order id: {}", savedOrder.getId());
                    }
                });

        return savedOrder;
    }

    public Order getOrderById(int orderId){
        log.debug("order service: getOrderById, id: {}", orderId);
        return orderRepository.findById(orderId).orElseThrow();
    }

    public List<Order> getAll(){
        log.debug("order service: getAll");
        return orderRepository.findAll();
    }
}
