package com.gtelant.commerce_service.repositories;

import com.gtelant.commerce_service.models.PtReviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PtReviewRepository extends JpaRepository<PtReviews,Integer>, JpaSpecificationExecutor<PtReviews> {
}
