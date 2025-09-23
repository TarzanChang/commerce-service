package com.gtelant.commerce_service.controllers;

import com.gtelant.commerce_service.mappers.ProductMapper;
import com.gtelant.commerce_service.mappers.PtReviewMapper;
import com.gtelant.commerce_service.mappers.UserMapper;
import com.gtelant.commerce_service.models.PtReviews;
import com.gtelant.commerce_service.requests.PtReviewRequest;
import com.gtelant.commerce_service.requests.UpdateReviewStatusRequest;
import com.gtelant.commerce_service.responses.ProductResponse;
import com.gtelant.commerce_service.responses.PtReviewResponse;
import com.gtelant.commerce_service.responses.UserResponse;
import com.gtelant.commerce_service.services.PtReviewService;
import com.gtelant.commerce_service.services.ProductService;
import com.gtelant.commerce_service.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/productReviews")
@SecurityRequirement(name = "bearerAuth")
public class PtReviewsController {
    @Autowired
    private PtReviewMapper ptReviewMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private PtReviewService ptReviewService;

    @Operation(summary = "Get all reviews.",description = "Return a list of all reviews.")
    @GetMapping
    public ResponseEntity<List<PtReviewResponse>> getAllReviews(){
        List<PtReviewResponse> responses = ptReviewService.getAllReviews()
                .stream()
                .map(ptReviews -> {
                    PtReviewResponse response = ptReviewMapper.toProductReviewResponse(ptReviews);
                    response.setUsers(new UserResponse(ptReviews.getUsers()));
                    response.setProducts(new ProductResponse(ptReviews.getProducts()));
                    return response;
                }).toList();
        return ResponseEntity.ok(responses);
    }

    @Operation(summary = "Create a new review.",description = "Creates a new review and returns the created review.")
    @PostMapping
    public ResponseEntity<PtReviewResponse> createProductReview(@RequestBody PtReviewRequest request){
        PtReviews reviews = ptReviewMapper.toProductReview(request);
        //為避免被繞過審核機制，Status 統一由後端邏輯帶入
//        reviews.setReviewStatus(ReviewStatus.PENDING);
        reviews.setUsers(userService.getUserById(request.getUserId()).orElseThrow(() -> new RuntimeException("User not found.")));
        reviews.setProducts(productService.getProductById(request.getProductId()).orElseThrow(() -> new RuntimeException("Product not found.")));
        PtReviews createdPtReviews = ptReviewService.createdProductReview(reviews);
        PtReviewResponse response = ptReviewMapper.toProductReviewResponse(createdPtReviews);
        response.setUsers(userMapper.toUserResponse(createdPtReviews.getUsers()));
        response.setProducts(productMapper.toProductResponse(createdPtReviews.getProducts()));
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Get all reviews pagination", description = "Returns a page of reviews")
    @GetMapping("/pageForReviews")
    public Page<PtReviewResponse> getAllReviewsPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "") String query,
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false) Integer userId,
            @RequestParam(required = false) Integer productId
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ptReviewService.getAllReviews(query, status, userId, productId, pageRequest).map(ptReviewMapper::toProductReviewResponse);
    }

    @Operation(summary = "Update review status.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Successfully retrieved category."),
            @ApiResponse(responseCode = "404",description = "Category not found.")
    })
    @PutMapping("/status")
    public ResponseEntity<List<PtReviewResponse>> updateReviewStatus(@RequestBody UpdateReviewStatusRequest request){
        List<PtReviews> reviews = ptReviewService.getptReviewByIds(request.getPtReviewIds());
        if(reviews.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        List<PtReviews> updatedReview = new ArrayList<>();
        for (PtReviews review : reviews){
            review.setReviewStatus(request.getReviewStatus());
            updatedReview.add(review);
        }
        List<PtReviews> createdPtreview = ptReviewService.updateReviews(updatedReview);
        List<PtReviewResponse> responses = createdPtreview.stream().map(review -> {
            PtReviewResponse response = ptReviewMapper.toProductReviewResponse(review);
            response.setUsers(userMapper.toUserResponse(review.getUsers()));
            response.setProducts(productMapper.toProductResponse(review.getProducts()));
            return response;
        }).toList();
        return ResponseEntity.ok(responses);
    }
}
