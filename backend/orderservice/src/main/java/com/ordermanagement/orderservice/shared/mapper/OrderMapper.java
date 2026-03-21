package com.ordermanagement.orderservice.shared.mapper;

import com.ordermanagement.orderservice.Models.Entity.Order;
import com.ordermanagement.orderservice.Models.dto.request.SaveOrderRequest;
import com.ordermanagement.orderservice.Models.dto.response.GetOrderResponse;
import com.ordermanagement.orderservice.Models.dto.response.SavedOrderResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class})
public interface OrderMapper {
    SavedOrderResponse toSavedResponse(Order order);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "paymentId", ignore = true)
    @Mapping(target = "orderStatus", ignore = true)
    @Mapping(target = "totalAmount", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Order saveRequestToEntity(SaveOrderRequest request);

    @Mapping(target = "items", source = "items")
    GetOrderResponse toGetResponse(Order order);

    List<GetOrderResponse> toGetResponses(List<Order> order);

    @AfterMapping
    default void linkItems(@MappingTarget Order order) {
        if (order.getItems() != null) {
            order.getItems().forEach(order::addItem);
        }
    }
}
