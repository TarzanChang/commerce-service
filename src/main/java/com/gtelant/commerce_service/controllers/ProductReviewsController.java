package com.gtelant.commerce_service.controllers;

import com.gtelant.commerce_service.mappers.ProductReviewMapper;
import com.gtelant.commerce_service.models.ProductReviews;
import com.gtelant.commerce_service.requests.ProductReviewRequest;
import com.gtelant.commerce_service.responses.ProductReviewResponse;
import com.gtelant.commerce_service.services.ProductReviewService;
import com.gtelant.commerce_service.services.ProductService;
import com.gtelant.commerce_service.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productReviews")
public class ProductReviewsController {
    @Autowired
    private ProductReviewMapper productReviewMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductReviewService productReviewService;

    @Operation(summary = "Create a new review.",description = "Creates a new review and returns the created review.")
    @PostMapping
    public ResponseEntity<ProductReviewResponse> createProductReview(@RequestBody ProductReviewRequest request){
        ProductReviews reviews = productReviewMapper.toProductReview(request);
        reviews.setUsers(userService.getUserById(request.getUserId()).orElseThrow(() -> new RuntimeException("User not found.")));
        reviews.setProducts(productService.getProductById(request.getProductId()).orElseThrow(() -> new RuntimeException("Product not found.")));
        ProductReviews productReviews = productReviewMapper.toProductReview(request);
        ProductReviews createdProductReviews = productReviewService.createProductReview(productReviews);
        return ResponseEntity.ok(productReviewMapper.toProductReviewResponse(createdProductReviews));
    }
}
