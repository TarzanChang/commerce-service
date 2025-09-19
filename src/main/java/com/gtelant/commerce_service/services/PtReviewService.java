package com.gtelant.commerce_service.services;

import com.gtelant.commerce_service.models.PtReviews;
import com.gtelant.commerce_service.repositories.PtReviewRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PtReviewService {
    @Autowired
    private PtReviewRepository ptReviewRepository;

    public List<PtReviews> getAllReviews(){
        return ptReviewRepository.findAll();
    }
    public PtReviews getReviewById(int id){
        Optional<PtReviews> reviewsOptional = ptReviewRepository.findById(id);
        return reviewsOptional.orElseThrow(() -> new RuntimeException("Review not found."));
    }
    public PtReviews createdProductReview(PtReviews ptReviews){
        return ptReviewRepository.save(ptReviews);
    }

    public Page<PtReviews> getAllReviews(String queryName, Integer status, Integer userId, Integer productId, PageRequest pageRequest) {
        Specification<PtReviews> spec = reviewsSpecification(queryName,status,userId,productId);
        return ptReviewRepository.findAll(spec,pageRequest);
    }
    public Specification<PtReviews> reviewsSpecification(String queryName, Integer status, Integer userId, Integer productId){
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(queryName != null && !queryName.isEmpty()) {
                predicates.add(criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder
                                .lower(root.get("comment")), "%"+ queryName.toLowerCase()+"%")
                ));
            }
            if (status != null){
                predicates.add(criteriaBuilder.equal(root.get("reviewStatus").get("reviewStatus"),status));
            }
            if (userId != null){
                predicates.add(criteriaBuilder.equal(root.get("users").get("userId"),userId));
            }
            if (productId != null){
                predicates.add(criteriaBuilder.equal(root.get("products").get("productId"),productId));
            }
            Predicate[] predicateArray = predicates.toArray(new Predicate[0]);
            return criteriaBuilder.and(predicateArray);
        });
    }

    public List<PtReviews> getptReviewByIds(List<Integer> ptReviewIds){
        return ptReviewRepository.findAllById(ptReviewIds);
    }

    public PtReviews updateReview(PtReviews reviews) {
        return ptReviewRepository.save(reviews);
    }

    public List<PtReviews> updateReviews(List<PtReviews> reviews){
        return ptReviewRepository.saveAll(reviews);
    }
}
