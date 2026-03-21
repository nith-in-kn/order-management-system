package com.ordermanagement.orderservice.shared.mapper;

import com.ordermanagement.orderservice.Models.Entity.OrderItem;
import com.ordermanagement.orderservice.Models.dto.request.OrderItemRequest;
import com.ordermanagement.orderservice.Models.dto.response.OrderItemResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    OrderItemResponse toItemResponse(OrderItem item);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "subTotal", ignore = true)
    OrderItem toItemEntity(OrderItemRequest request);
}
