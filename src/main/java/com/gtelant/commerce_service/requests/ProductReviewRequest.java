package com.gtelant.commerce_service.requests;

import com.gtelant.commerce_service.enums.ReviewStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductReviewRequest {
    private int userId;
    private int productId;
    private int rating;
    private String comment;
    private ReviewStatus status;
    private LocalDateTime creationDate;
    private String createdBy;
    private LocalDateTime lastUpdateDate;
    private String lastUpdatedBy;
}
