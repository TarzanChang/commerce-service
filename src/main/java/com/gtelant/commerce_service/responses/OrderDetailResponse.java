package com.gtelant.commerce_service.responses;

import com.gtelant.commerce_service.models.OrderDetail;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailResponse {
    private int odDerailId;
    private int quantity;
    private LocalDateTime deleteAt;
    private LocalDateTime creationDate;
    private String createdBy;
    private LocalDateTime lastUpdateDate;
    private String lastUpdatedBy;

    public OrderDetailResponse(OrderDetail orderDetail){
        this.odDerailId = orderDetail.getOdDerailId();
        this.quantity = orderDetail.getQuantity();
        this.deleteAt = orderDetail.getDeleteAt();
        this.creationDate = orderDetail.getCreationDate();
        this.createdBy = orderDetail.getCreatedBy();
        this.lastUpdateDate = orderDetail.getLastUpdateDate();
        this.lastUpdatedBy = orderDetail.getLastUpdatedBy();
    }
}
