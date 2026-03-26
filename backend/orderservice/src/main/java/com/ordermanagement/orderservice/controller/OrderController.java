package com.ordermanagement.orderservice.controller;

import com.ordermanagement.orderservice.Models.dto.request.SaveOrderRequest;
import com.ordermanagement.orderservice.Models.dto.response.GetOrderResponse;
import com.ordermanagement.orderservice.Models.dto.response.SavedOrderResponse;
import com.ordermanagement.orderservice.exceptions.custom.OrderNotFoundException;
import com.ordermanagement.orderservice.service.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController @RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {

    private OrderService service;

    @GetMapping
    public ResponseEntity<List<GetOrderResponse>> getAll() throws OrderNotFoundException {
        log.info("GET /api/orders - fetching all orders");
        List<GetOrderResponse> orders = service.getAllOrder();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetOrderResponse> getAnOrder(@PathVariable String id) throws OrderNotFoundException {
        log.info("GET /api/orders/{} - fetching all orders", id);
        GetOrderResponse order = service.getOrder(id);
        return new ResponseEntity<>(order, HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<SavedOrderResponse> saveOrder(@Valid @RequestBody SaveOrderRequest requestBody){
        log.info("POST /api/orders - creating order for customer: {}",
                requestBody.getCustomerId());
        SavedOrderResponse order = service.saveOrder(requestBody);log.info("POST /api/orders - creating order for customer: {}",
                requestBody.getCustomerId());
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}
