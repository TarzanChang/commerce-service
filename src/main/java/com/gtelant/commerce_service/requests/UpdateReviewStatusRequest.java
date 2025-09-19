package com.gtelant.commerce_service.requests;

import com.gtelant.commerce_service.enums.ReviewStatus;
import lombok.Data;

import java.util.List;

@Data
public class UpdateReviewStatusRequest {
//    private int ptReviewId;
    private List<Integer> ptReviewIds;
    private ReviewStatus reviewStatus;

}
