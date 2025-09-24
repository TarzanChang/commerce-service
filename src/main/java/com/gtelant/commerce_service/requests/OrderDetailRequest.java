package com.gtelant.commerce_service.requests;

import com.gtelant.commerce_service.models.Orders;
import com.gtelant.commerce_service.models.Products;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailRequest {
    private int quantity;
    private LocalDateTime deleteAt;
    private LocalDateTime creationDate;
    private String createdBy;
    private LocalDateTime lastUpdateDate;
    private String lastUpdatedBy;
//    private Orders orders;
//    private Products products;
}
