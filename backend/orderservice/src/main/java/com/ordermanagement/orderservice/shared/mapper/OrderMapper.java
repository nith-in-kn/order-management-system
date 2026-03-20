package com.ordermanagement.orderservice.shared.mapper;

import com.ordermanagement.orderservice.Models.Entity.Order;
import com.ordermanagement.orderservice.Models.dto.request.SaveOrderRequest;
import com.ordermanagement.orderservice.Models.dto.response.GetOrderResponse;
import com.ordermanagement.orderservice.Models.dto.response.SavedOrderResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    SavedOrderResponse toSavedResponse(Order order);
    Order saveRequestToEntity(SaveOrderRequest request);
    GetOrderResponse toGetResponse(Order order);
    List<GetOrderResponse> toGetResponses(List<Order> order);
}
