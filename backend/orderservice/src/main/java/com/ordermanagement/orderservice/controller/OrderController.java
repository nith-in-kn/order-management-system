package com.ordermanagement.orderservice.controller;

import com.ordermanagement.orderservice.entity.Order;
import com.ordermanagement.orderservice.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController @AllArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    private OrderService orderService;

    @PostMapping()
    public ResponseEntity<Order> saveOrder(
            @RequestBody Order order
    ){
        log.debug("inside OrderController::saveOrder");
        return ResponseEntity.ok(
//                new Order()
                orderService.saveOrder(order)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(
            @PathVariable int id
    ){
        log.debug("/api/order/{id} id:{}",id);
        return ResponseEntity.ok(
//                new Order()
                orderService.getOrderById(id)
        );
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAll(){
        return ResponseEntity.ok(
//                new ArrayList<Order>()
                orderService.getAll()
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(
            Exception exception,
            HttpServletRequest request
    ){
        log.error("Exception occurred for uri: {} | Message: {} ", request.getRequestURL(), exception.getMessage(), exception);
        return ResponseEntity.internalServerError().build();
    }
}
