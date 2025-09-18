package com.gtelant.commerce_service.responses;

import com.gtelant.commerce_service.enums.ReviewStatus;
import com.gtelant.commerce_service.models.Products;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductReviewResponse {
    private Integer ptReviewId;
    private UserResponse users;
    private Products products;
    private int rating;
    private String comment;
    private ReviewStatus status;
    private LocalDateTime creationDate;
    private String createdBy;
    private LocalDateTime lastUpdateDate;
    private String lastUpdatedBy;
}
