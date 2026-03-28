package com.productmanagement.productservice.service;

import com.productmanagement.productservice.dto.OrderEvent;
import com.productmanagement.productservice.entity.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryService {

    private final ProductService productService;

    @KafkaListener(
            topics = "orders",
            groupId = "inventory-service-group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    @Transactional
    public void handleOrderEvent(OrderEvent orderEvent) {
        log.info("inventory-service-group::Received order event: {}", orderEvent);

        try {
            if (orderEvent.getItems() != null && !orderEvent.getItems().isEmpty()) {
                for (OrderEvent.OrderItemEvent item : orderEvent.getItems()) {
                    processOrderItem(item);
                }
            }

            log.info("Successfully processed order event for {} items", orderEvent.getItems() != null ? orderEvent.getItems().size() : 0);

        } catch (Exception e) {
            log.error("Failed to process order event: {}", e.getMessage(), e);
            // In a real application, you might want to send this to a dead letter topic
        }
    }

    private void processOrderItem(OrderEvent.OrderItemEvent item) {
        try {
            Integer productId = item.getProductId();
            Integer quantity = item.getQuantity();

            if (productId == null || quantity == null) {
                log.warn("Invalid order item data: productId={}, quantity={}", productId, quantity);
                return;
            }

            log.info("Processing order item: productId={}, quantity={}", productId, quantity);

            // Check if product exists and has sufficient stock
            Product product = null;
            try {
                product = productService.findById(productId);
            } catch (Exception e) {
                log.error("Product not found: {}", productId);
                return;
            }

            if (product.getStockQuantity() < quantity) {
                log.error("Insufficient stock for product {}: requested={}, available={}",
                         productId, quantity, product.getStockQuantity());
                // In a real application, you might want to publish an "order failed" event
                return;
            }

            // Reduce stock
            product.setStockQuantity(product.getStockQuantity() - quantity);
            productService.save(product);

            log.info("Updated stock for product {}: new quantity={}",
                    productId, product.getStockQuantity());

            // Check for low stock alert
            if (product.getStockQuantity() <= 5) { // Low stock threshold
                log.warn("LOW STOCK ALERT: Product {} has only {} items remaining",
                        product.getName(), product.getStockQuantity());
            }

        } catch (Exception e) {
            log.error("Error processing order item: {}", e.getMessage(), e);
        }
    }
}