package com.gtelant.commerce_service.responses;

import com.gtelant.commerce_service.models.Products;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private int productId;
    private String productName;
    private String imageUrl ;
    private String thumbnailUrl;
    private Double width;
    private Double height;
    private BigDecimal price;
    private Integer stock;
    private Integer sales;
    private String description;
    private LocalDateTime deleteAt;
    private LocalDateTime creationDate;
    private String createdBy;
    private LocalDateTime lastUpdateDate;
    private String lastUpdatedBy;
    private CategoryResponse category;

    private List<CategoryResponse> categoryResponseList;

    public ProductResponse(Products products) {
        this.productId = products.getProductId();
        this.productName = products.getProductName();
        this.imageUrl = products.getImageUrl();
        this.thumbnailUrl = products.getThumbnailUrl();
        this.width = products.getWidth();
        this.height = products.getHeight();
        this.price = products.getPrice();
        this.stock = products.getStock();
        this.sales = products.getSales();
        this.description = products.getDescription();
        this.deleteAt = products.getDeleteAt();
        this.creationDate = products.getCreationDate();
        this.createdBy = products.getCreatedBy();
        this.lastUpdateDate = products.getLastUpdateDate();
        this.lastUpdatedBy = products.getLastUpdatedBy();
    }

//    public ProductResponse() {
//    }
}
