package com.gtelant.commerce_service.requests;

import com.gtelant.commerce_service.enums.OrderStatus;
import com.gtelant.commerce_service.models.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private LocalDate orderDate;
    private LocalDateTime deleteAt;
    private LocalDateTime creationDate;
    private String createdBy;
    private LocalDateTime lastUpdateDate;
    private String lastUpdatedBy;
    private Users users;
}
