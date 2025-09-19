package com.gtelant.commerce_service.requests;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductRequest {
    private String productName;
    private String imageUrl ;
    private String thumbnailUrl;
    private Double width;
    private Double height;
    private BigDecimal price;
    private Integer stock;
    private Integer sales;
    private String description;
    private LocalDateTime creationDate;
    private String createdBy;
    private LocalDateTime lastUpdateDate;
    private String lastUpdatedBy;
    private Integer categoryId;
}
