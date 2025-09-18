package com.gtelant.commerce_service.services;

import com.gtelant.commerce_service.models.ProductReviews;
import com.gtelant.commerce_service.repositories.ProductReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductReviewService {
    @Autowired
    private ProductReviewRepository productReviewRepository;

    public ProductReviews createProductReview(ProductReviews productReviews){
        return productReviewRepository.save(productReviews);
    }
}
