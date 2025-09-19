package com.gtelant.commerce_service.responses;

import com.gtelant.commerce_service.enums.ReviewStatus;
import com.gtelant.commerce_service.models.PtReviews;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PtReviewResponse {
    private int ptReviewId;
    private UserResponse users;
    private ProductResponse products;
    private int rating;
    private String comment;
    private ReviewStatus status;
    private LocalDateTime creationDate;
    private String createdBy;
    private LocalDateTime lastUpdateDate;
    private String lastUpdatedBy;

//    private List<UserResponse> userResponseList;
//    private List<ProductResponse> productResponseList;

    public PtReviewResponse(PtReviews ptReviews) {
    }
}
