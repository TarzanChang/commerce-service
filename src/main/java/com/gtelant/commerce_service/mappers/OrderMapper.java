package com.gtelant.commerce_service.mappers;

import com.gtelant.commerce_service.models.OrderDetail;
import com.gtelant.commerce_service.models.Orders;
import com.gtelant.commerce_service.repositories.OrderDetailRepository;
import com.gtelant.commerce_service.repositories.OrderRepository;
import com.gtelant.commerce_service.requests.OrderRequest;
import com.gtelant.commerce_service.responses.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderResponse toOrderResponse(Orders orders){
        OrderResponse dto = new OrderResponse();
        dto.setOrderId(orders.getOrderId());
        dto.setOrderStatus(orders.getOrderStatus());
        dto.setOrderDate(orders.getOrderDate());
//        dto.setDeleteAt(orders.getDeleteAt());
        dto.setCreatedBy(orders.getCreatedBy());
        dto.setCreationDate(orders.getCreationDate());
        dto.setLastUpdatedBy(orders.getLastUpdatedBy());
        dto.setLastUpdateDate(orders.getLastUpdateDate());
        return dto;
    }

    public Orders toOrders(OrderRequest dto){
        Orders orders = new Orders();
        orders.setOrderDate(dto.getOrderDate());
        orders.setCreatedBy(dto.getCreatedBy());
        orders.setCreationDate(dto.getCreationDate());
        orders.setLastUpdatedBy(dto.getLastUpdatedBy());
        orders.setLastUpdateDate(dto.getLastUpdateDate());
        orders.setUsers(dto.getUsers());
        return orders;
    }

//    public OrderDetailResponse toOrderDetailOrderResponse(Orders orders);{
//        OrderDetailResponse response = new OrderDetailResponse();
//        response.setOdDerailId(toOrders().getOrderId());
//    }
}
