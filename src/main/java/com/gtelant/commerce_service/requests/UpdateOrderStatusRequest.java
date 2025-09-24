package com.gtelant.commerce_service.requests;

import com.gtelant.commerce_service.enums.OrderStatus;
import lombok.Data;

@Data
public class UpdateOrderStatusRequest {
    private Integer orderId;
    private OrderStatus orderStatus;
    private boolean returned;
}
