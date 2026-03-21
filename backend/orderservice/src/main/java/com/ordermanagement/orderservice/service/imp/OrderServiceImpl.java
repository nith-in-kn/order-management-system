package com.ordermanagement.orderservice.service.imp;

import com.ordermanagement.orderservice.Models.Entity.Order;
import com.ordermanagement.orderservice.Models.Entity.OrderItem;
import com.ordermanagement.orderservice.Models.dto.request.OrderItemRequest;
import com.ordermanagement.orderservice.Models.dto.request.SaveOrderRequest;
import com.ordermanagement.orderservice.Models.dto.response.GetOrderResponse;
import com.ordermanagement.orderservice.Models.dto.response.SavedOrderResponse;
import com.ordermanagement.orderservice.exceptions.custom.OrderNotFoundException;
import com.ordermanagement.orderservice.repository.OrderRepository;
import com.ordermanagement.orderservice.service.OrderService;
import com.ordermanagement.orderservice.shared.mapper.OrderItemMapper;
import com.ordermanagement.orderservice.shared.mapper.OrderMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service @AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private OrderRepository repository;
    private OrderMapper orderMapper;
    private OrderItemMapper orderItemMapper;

    @Override
    public List<GetOrderResponse> getAllOrder() throws OrderNotFoundException {
        List<Order> orders = repository.findAll();
        if(orders.isEmpty()) throw new OrderNotFoundException("No Order exist!");

        return orderMapper.toGetResponses(orders) ;
    }

    @Override
    public GetOrderResponse getOrder(String orderId) throws OrderNotFoundException {
        return orderMapper.toGetResponse(
                repository.findById(orderId)
                .orElseThrow(()->new OrderNotFoundException("Order "+ orderId +", doesn't exist!")));
    }

    @Transactional
    public SavedOrderResponse saveOrder(SaveOrderRequest request) {

        Order newOrder = orderMapper.saveRequestToEntity(request);

//        newOrder.setCustomerId(request.getCustomerId());
//        newOrder.setCustomerName(request.getCustomerName());
//        newOrder.setDeliveryAddress(request.getDeliveryAddress());

//        request.getItems().stream()
//                .map(this::buildOrderItem)
//                .forEach(newOrder::addItem);

        BigDecimal total = newOrder.getItems().stream()
                .map(OrderItem::getSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        newOrder.setTotalAmount(total);

        return orderMapper.toSavedResponse(repository.save(newOrder));
    }

    private OrderItem buildOrderItem(OrderItemRequest request) {
        OrderItem item = orderItemMapper.toItemEntity(request);
        item.setSubTotal(
                request.getUnitPrice()
                        .multiply(BigDecimal.valueOf(request.getQuantity()))
        );
        return item;
    }
}
