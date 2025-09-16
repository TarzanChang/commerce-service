package com.gtelant.commerce_service.mappers;

import com.gtelant.commerce_service.models.Products;
import com.gtelant.commerce_service.requests.ProductRequest;
import com.gtelant.commerce_service.responses.ProductResponse;
import org.springframework.stereotype.Component;


@Component
public class ProductMapper {
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
        return dto;
    }

    public Products toProduct(ProductRequest dto){
        Products products = new Products();
        products.setProductName(dto.getProductName());
        products.setImageUrl(dto.getImageUrl());
        products.setThumbnailUrl(dto.getThumbnailUrl());
        products.setWidth(dto.getWidth());
        products.setHeight(dto.getHeight());
        products.setPrice(dto.getPrice());
        products.setStock(dto.getStock());
        products.setSales(dto.getSales());
        products.setDescription(dto.getDescription());
        products.setDeleteAt(dto.getDeleteAt());
        products.setCreatedBy(dto.getCreatedBy());
        products.setCreationDate(dto.getCreationDate());
        products.setLastUpdatedBy(dto.getLastUpdatedBy());
        products.setLastUpdateDate(dto.getLastUpdateDate());
        return products;

    }
}
