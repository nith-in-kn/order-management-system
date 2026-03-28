package com.ordermanagement.orderservice.service.impl;

import com.ordermanagement.orderservice.dto.OrderEvent;
import com.ordermanagement.orderservice.entity.Order;
import com.ordermanagement.orderservice.repository.OrderRepository;
import com.ordermanagement.orderservice.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service @AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    @Transactional
    public Order saveOrder(Order order){
        log.debug("order service: saveOrder, order: {}", order);

        Order savedOrder = orderRepository.save(order);

        // Create order event DTO for Kafka
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setId(savedOrder.getId());
        orderEvent.setCustomerId(savedOrder.getCustomerId());
        orderEvent.setPaymentId(savedOrder.getPaymentId());
        orderEvent.setOrderDate(savedOrder.getCreatedAt());
        orderEvent.setTotalAmount(savedOrder.getTotalAmount());
        orderEvent.setItems(savedOrder.getItems().stream()
                .map(item -> {
                    OrderEvent.OrderItemEvent itemEvent = new OrderEvent.OrderItemEvent();
                    itemEvent.setProductId(item.getProductId());
                    itemEvent.setName(item.getName());
                    itemEvent.setUnitPrice(item.getUnitPrice());
                    itemEvent.setQuantity(item.getQuantity());
                    itemEvent.setSubTotal(item.getSubTotal());
                    return itemEvent;
                })
                .collect(Collectors.toList()));

        kafkaTemplate
                .send("orders", String.valueOf(savedOrder.getId()), orderEvent)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        log.error("Failed to publish order to Kafka: ", ex);
                    } else {
                        log.debug("Order published to Kafka! Topic: {}, Partition: {}, Offset: {}",
                                result.getRecordMetadata().topic(),
                                result.getRecordMetadata().partition(),
                                result.getRecordMetadata().offset());
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
