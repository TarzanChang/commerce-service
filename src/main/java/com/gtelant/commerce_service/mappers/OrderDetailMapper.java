package com.gtelant.commerce_service.mappers;

import com.gtelant.commerce_service.models.OrderDetail;
import com.gtelant.commerce_service.requests.OrderDetailRequest;
import com.gtelant.commerce_service.responses.OrderDetailResponse;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailMapper {

    public OrderDetailResponse toOrderDetailResponse(OrderDetail orderDetail){
        OrderDetailResponse dto = new OrderDetailResponse();
        dto.setOdDerailId(orderDetail.getOdDerailId());
        dto.setQuantity(orderDetail.getQuantity());
        dto.setCreatedBy(orderDetail.getCreatedBy());
        dto.setCreationDate(orderDetail.getCreationDate());
        dto.setLastUpdatedBy(orderDetail.getLastUpdatedBy());
        dto.setLastUpdateDate(orderDetail.getLastUpdateDate());
        return dto;
    }

    public OrderDetail toOrderDetail(OrderDetailRequest dto){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setQuantity(dto.getQuantity());
        orderDetail.setCreatedBy(dto.getCreatedBy());
        orderDetail.setCreationDate(dto.getCreationDate());
        orderDetail.setLastUpdatedBy(dto.getLastUpdatedBy());
        orderDetail.setLastUpdateDate(dto.getLastUpdateDate());
//        orderDetail.setOrders(dto.getOrders());
//        orderDetail.setProducts(dto.getProducts());
        return orderDetail;
    }
}
