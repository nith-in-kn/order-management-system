package com.ordermanagement.orderservice.service.imp;

import com.ordermanagement.orderservice.Models.Entity.Order;
import com.ordermanagement.orderservice.Models.Entity.OrderItem;
import com.ordermanagement.orderservice.Models.dto.request.SaveOrderRequest;
import com.ordermanagement.orderservice.Models.dto.response.GetOrderResponse;
import com.ordermanagement.orderservice.Models.dto.response.SavedOrderResponse;
import com.ordermanagement.orderservice.exceptions.custom.OrderNotFoundException;
import com.ordermanagement.orderservice.repository.OrderRepository;
import com.ordermanagement.orderservice.service.OrderService;
import com.ordermanagement.orderservice.shared.enums.OrderStatus;
import com.ordermanagement.orderservice.shared.mapper.OrderMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service @AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private OrderRepository repository;
    private OrderMapper mapper;

    @Override
    public List<GetOrderResponse> getAllOrder() throws OrderNotFoundException {
        List<Order> orders = repository.findAll();
        if(orders.isEmpty()) throw new OrderNotFoundException("No Order exist!");

        return mapper.toGetResponses(orders) ;
    }

    @Override
    public GetOrderResponse getOrder(String orderId) throws OrderNotFoundException {
        return mapper.toGetResponse(
                repository.findById(orderId)
                .orElseThrow(()->new OrderNotFoundException("Order "+ orderId +", doesn't exist!")));
    }

    @Override
    public SavedOrderResponse saveOrder(SaveOrderRequest order) {
        Order newOrder = mapper.saveRequestToEntity(order);

        BigDecimal total = newOrder.getItems().stream()
                .map(OrderItem::getSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        newOrder.setTotalAmount(total);
        newOrder.setOrderStatus(OrderStatus.PENDING);

        Order savedOrder = repository.save(newOrder);
        return mapper.toSavedResponse(savedOrder);
    }
}
