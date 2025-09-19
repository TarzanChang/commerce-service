package com.gtelant.commerce_service.requests;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PtReviewRequest {
    private int userId;
    private int productId;
    private int rating;
    private String comment;
    private LocalDateTime creationDate;
    private String createdBy;
    private LocalDateTime lastUpdateDate;
    private String lastUpdatedBy;
}
