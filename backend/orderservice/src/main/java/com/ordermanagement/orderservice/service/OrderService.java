package com.ordermanagement.orderservice.service;

import com.ordermanagement.orderservice.Models.Entity.Order;
import com.ordermanagement.orderservice.Models.dto.request.SaveOrderRequest;
import com.ordermanagement.orderservice.Models.dto.response.GetOrderResponse;
import com.ordermanagement.orderservice.Models.dto.response.SavedOrderResponse;
import com.ordermanagement.orderservice.exceptions.custom.OrderNotFoundException;

import java.util.List;

public interface OrderService {
    List<GetOrderResponse> getAllOrder() throws OrderNotFoundException;
    GetOrderResponse getOrder(String orderId) throws OrderNotFoundException;
    SavedOrderResponse saveOrder(SaveOrderRequest order);
}
