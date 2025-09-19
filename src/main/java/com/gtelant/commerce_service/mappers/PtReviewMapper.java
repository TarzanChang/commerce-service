package com.gtelant.commerce_service.mappers;

import com.gtelant.commerce_service.models.PtReviews;
import com.gtelant.commerce_service.requests.PtReviewRequest;
import com.gtelant.commerce_service.responses.PtReviewResponse;
import org.springframework.stereotype.Component;

@Component
public class PtReviewMapper {
    public PtReviews toProductReview(PtReviewRequest request){
        PtReviews ptReviews = new PtReviews();
        ptReviews.setRating(request.getRating());
        ptReviews.setComment(request.getComment());
        ptReviews.setCreationDate(request.getCreationDate());
        ptReviews.setCreatedBy(request.getCreatedBy());
        ptReviews.setLastUpdateDate(request.getLastUpdateDate());
        ptReviews.setLastUpdatedBy(request.getLastUpdatedBy());
        return ptReviews;
    }

    public PtReviewResponse toProductReviewResponse(PtReviews review){
        PtReviewResponse dto = new PtReviewResponse();
        dto.setPtReviewId(review.getPtReviewId());
        dto.setRating(review.getRating());
        dto.setStatus(review.getReviewStatus());
        dto.setComment(review.getComment());
        dto.setCreationDate(review.getCreationDate());
        dto.setCreatedBy(review.getCreatedBy());
        dto.setLastUpdateDate(review.getLastUpdateDate());
        dto.setLastUpdatedBy(review.getLastUpdatedBy());
        return dto;
    }
}
