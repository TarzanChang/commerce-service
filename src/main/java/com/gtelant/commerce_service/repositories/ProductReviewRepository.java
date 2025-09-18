package com.gtelant.commerce_service.repositories;

import com.gtelant.commerce_service.models.ProductReviews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReviewRepository extends JpaRepository<ProductReviews,Integer> {
}
