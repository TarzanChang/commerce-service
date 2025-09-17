package com.gtelant.commerce_service.mappers;

import com.gtelant.commerce_service.models.Categories;
import com.gtelant.commerce_service.models.Products;
import com.gtelant.commerce_service.repositories.CategoryRepository;
import com.gtelant.commerce_service.requests.ProductRequest;
import com.gtelant.commerce_service.responses.CategoryProductResponse;
import com.gtelant.commerce_service.responses.ProductResponse;
import org.springframework.stereotype.Component;


@Component
public class ProductMapper {

    private final CategoryRepository categoryRepository;

    public ProductMapper(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ProductResponse toProductResponse(Products products){
        ProductResponse dto = new ProductResponse();
        dto.setProductId(products.getProductId());
        dto.setProductName(products.getProductName());
        dto.setImageUrl(products.getImageUrl());
        dto.setThumbnailUrl(products.getThumbnailUrl());
        dto.setWidth(products.getWidth());
        dto.setHeight(products.getHeight());
        dto.setPrice(products.getPrice());
        dto.setStock(products.getStock());
        dto.setSales(products.getSales());
        dto.setDescription(products.getDescription());
        dto.setDeleteAt(products.getDeleteAt());
        dto.setCreatedBy(products.getCreatedBy());
        dto.setCreationDate(products.getCreationDate());
        dto.setLastUpdatedBy(products.getLastUpdatedBy());
        dto.setLastUpdateDate(products.getLastUpdateDate());
//        dto.setCategory(products.getCategories().get);
        return dto;
    }

    public Products toProduct(ProductRequest dto,Products original,Categories categories){
        Products products = new Products();

        if(dto.getProductName() != null && !dto.getProductName().isEmpty()){
            products.setProductName(dto.getProductName());
        }else {
            products.setProductName(original.getProductName());
        }
        if(dto.getImageUrl() != null && !dto.getImageUrl().isEmpty()){
            products.setImageUrl(dto.getImageUrl());
        }else {
            products.setImageUrl(original.getImageUrl());
        }
        if(dto.getThumbnailUrl() != null && !dto.getThumbnailUrl().isEmpty()){
            products.setThumbnailUrl(dto.getThumbnailUrl());
        }else {
            products.setThumbnailUrl(original.getThumbnailUrl());
        }
        if(dto.getWidth() != null){
            products.setWidth(dto.getWidth());
        }else {
            products.setWidth(original.getWidth());
        }
        if(dto.getHeight() != null){
            products.setHeight(dto.getHeight());
        }else {
            products.setHeight(original.getHeight());
        }
        if(dto.getPrice() != null){
            products.setPrice(dto.getPrice());
        }else {
            products.setPrice(original.getPrice());
        }
        if(dto.getStock() != null){
            products.setStock(dto.getStock());
        }else {
            products.setStock(original.getStock());
        }
        if(dto.getSales() != null){
            products.setSales(dto.getSales());
        }else {
            products.setSales(original.getSales());
        }
        if(dto.getDescription() != null && !dto.getDescription().isEmpty()){
            products.setDescription(dto.getDescription());
        }else {
            products.setDescription(original.getDescription());
        }
        if(dto.getDeleteAt() != null){
            products.setDeleteAt(dto.getDeleteAt());
        }else {
            products.setDeleteAt(original.getDeleteAt());
        }
        products.setCreatedBy(dto.getCreatedBy());
        products.setCreationDate(dto.getCreationDate());
        products.setLastUpdatedBy(dto.getLastUpdatedBy());
        products.setLastUpdateDate(dto.getLastUpdateDate());
        if(categories != null){
            products.setCategories(categories);
        }else {
            products.setCategories(original.getCategories());
        }
        return products;
    }

    public CategoryProductResponse toCategoryProductResponse(Products products) {
        CategoryProductResponse response = new CategoryProductResponse();
        response.setCategoryProductId(products.getProductId());
        response.setProductName(products.getProductName());
        return response;
    }
}
