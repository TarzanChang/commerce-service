package com.gtelant.commerce_service.responses;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CategoryResponse {
    private int categoryId;
    private String categoryName;
    private LocalDateTime deleteAt;
    private LocalDateTime creationDate;
    private String createdBy;
    private LocalDateTime lastUpdateDate;
    private String lastUpdatedBy;
}
