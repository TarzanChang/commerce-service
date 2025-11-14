package com.gtelant.commerce_service.controllers;

import com.gtelant.commerce_service.mappers.CategoryMapper;
import com.gtelant.commerce_service.mappers.ProductMapper;
import com.gtelant.commerce_service.models.Categories;
import com.gtelant.commerce_service.requests.CategoryRequest;
import com.gtelant.commerce_service.responses.CategoryProductResponse;
import com.gtelant.commerce_service.responses.CategoryResponse;
import com.gtelant.commerce_service.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Categories", description = "Category management APIs.")
public class CategoryContorller {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ProductMapper productMapper;

    @Operation(summary = "Get all categories", description = "Returns a list of all categories")
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories(){
        List<CategoryResponse> responses = categoryService.getAllCategories().stream()
                .map(categories -> {
                    CategoryResponse response = categoryMapper.toCategoryResponse(categories);
                    List<CategoryProductResponse> productResponseList = categories
                            .getProductsList().stream()
                            .map(products -> productMapper.toCategoryProductResponse(products))
                            .toList();
                    response.setProductList(productResponseList);
                    return response;
                        }).toList();
        return ResponseEntity.ok(responses);
    }

    @Operation(summary = "Get category by ID.",description = "Returns a single category by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Successfully retrieved category."),
            @ApiResponse(responseCode = "404",description = "Segment not found.")})
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable int id){
        Categories categories = categoryService.getCategoryById(id);
        return ResponseEntity.ok(categoryMapper.toCategoryResponse(categories));
    }

    @Operation(summary = "Create a new category.",description = "Creates a new category and returns the created category.")
    @PostMapping
    public ResponseEntity<CategoryResponse> createcategory(@RequestBody CategoryRequest categoryRequest){
        Categories categories = categoryMapper.toCategory(categoryRequest);
        Categories createdSegment = categoryService.createCategory(categories);
        return ResponseEntity.ok(categoryMapper.toCategoryResponse(createdSegment));
    }

    @Operation(summary = "Update an existing category.",description = "Updates an existing category by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Successfully retrieved category."),
            @ApiResponse(responseCode = "404",description = "Category not found.")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable int id, @RequestBody CategoryRequest categoryRequest){
        Categories categories = categoryMapper.toCategory(categoryRequest);
        Categories updatedCategory = categoryService.updateCategory(id,categories);
        if(updatedCategory == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoryMapper.toCategoryResponse(updatedCategory));
    }

    @Operation(summary = "Delete a segment.",description = "Deletes a segment by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "Successfully deleted segment."),
            @ApiResponse(responseCode = "404",description = "segment not found.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id){
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
