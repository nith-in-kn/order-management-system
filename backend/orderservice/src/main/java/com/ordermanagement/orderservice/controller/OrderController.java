package com.ordermanagement.orderservice.controller;

import com.ordermanagement.orderservice.Models.dto.request.SaveOrderRequest;
import com.ordermanagement.orderservice.Models.dto.response.GetOrderResponse;
import com.ordermanagement.orderservice.Models.dto.response.SavedOrderResponse;
import com.ordermanagement.orderservice.exceptions.custom.OrderNotFoundException;
import com.ordermanagement.orderservice.service.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("order")
@AllArgsConstructor
public class OrderController {

    private OrderService service;

    @GetMapping
    public ResponseEntity<List<GetOrderResponse>> getAll() throws OrderNotFoundException {
        List<GetOrderResponse> orders = service.getAllOrder();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetOrderResponse> getAnOrder(@PathVariable String id) throws OrderNotFoundException {
        GetOrderResponse order = service.getOrder(id);
        return new ResponseEntity<>(order, HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<SavedOrderResponse> saveOrder(@Valid @RequestBody SaveOrderRequest requestBody){
        SavedOrderResponse order = service.saveOrder(requestBody);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}
