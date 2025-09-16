package com.gtelant.commerce_service.requests;

import com.gtelant.commerce_service.responses.CategoryResponse;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductRequest {
    private String productName;
    private String imageUrl ;
    private String thumbnailUrl;
    private double width;
    private double height;
    private BigDecimal price;
    private int stock;
    private int sales;
    private String description;
    private LocalDateTime deleteAt;
    private LocalDateTime creationDate;
    private String createdBy;
    private LocalDateTime lastUpdateDate;
    private String lastUpdatedBy;
}
