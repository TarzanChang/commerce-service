package com.gtelant.commerce_service.responses;

import com.gtelant.commerce_service.enums.OrderStatus;
import com.gtelant.commerce_service.models.OrderDetail;
import com.gtelant.commerce_service.models.Orders;
import com.gtelant.commerce_service.models.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private int orderId;
    private OrderStatus orderStatus;
    private LocalDate orderDate;
    private boolean returned;
    private LocalDateTime deleteAt;
    private LocalDateTime creationDate;
    private String createdBy;
    private LocalDateTime lastUpdateDate;
    private String lastUpdatedBy;
    private Users users;
    private ProductResponse productResponse;

    private List<UserResponse> userResponsesList;

//    private OrderDetail orderDetail;

    private OrderDetailResponse orderdetailResponse;

    public OrderResponse(Orders orders){
        this.orderId = orders.getOrderId();
        this.orderStatus = orders.getOrderStatus();
        this.orderDate = orders.getOrderDate();
        this.returned = orders.isReturned();
        this.deleteAt = orders.getDeleteAt();
        this.creationDate = orders.getCreationDate();
        this.createdBy = orders.getCreatedBy();
        this.lastUpdateDate = orders.getLastUpdateDate();
        this.lastUpdatedBy = orders.getLastUpdatedBy();
    }
}
