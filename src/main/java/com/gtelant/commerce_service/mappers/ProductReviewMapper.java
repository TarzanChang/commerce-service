package com.gtelant.commerce_service.mappers;

import com.gtelant.commerce_service.models.ProductReviews;
import com.gtelant.commerce_service.requests.ProductReviewRequest;
import com.gtelant.commerce_service.responses.ProductReviewResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductReviewMapper {
    public ProductReviews toProductReview(ProductReviewRequest request){
        ProductReviews productReviews = new ProductReviews();
        productReviews.setRating(request.getRating());
        productReviews.setReviewStatus(request.getStatus());
        productReviews.setComment(request.getComment());
        productReviews.setCreationDate(request.getCreationDate());
        productReviews.setCreatedBy(request.getCreatedBy());
        productReviews.setLastUpdateDate(request.getLastUpdateDate());
        productReviews.setLastUpdatedBy(request.getLastUpdatedBy());
        return productReviews;
    }

    public ProductReviewResponse toProductReviewResponse(ProductReviews review){
        ProductReviewResponse response = new ProductReviewResponse();
        response.setRating(review.getRating());
        response.setStatus(review.getReviewStatus());
        response.setComment(review.getComment());
        response.setCreationDate(review.getCreationDate());
        response.setCreatedBy(review.getCreatedBy());
        response.setLastUpdateDate(review.getLastUpdateDate());
        response.setLastUpdatedBy(review.getLastUpdatedBy());
        return response;
    }
}
