package com.gtelant.commerce_service.responses;

import com.gtelant.commerce_service.models.Categories;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
    private int categoryId;
    private String categoryName;
    private LocalDateTime deleteAt;
    private LocalDateTime creationDate;
    private String createdBy;
    private LocalDateTime lastUpdateDate;
    private String lastUpdatedBy;

    private List<CategoryProductResponse> productList;

    public CategoryResponse(Categories categories) {
        this.categoryId = categories.getCategoryId();
        this.categoryName = categories.getCategoryName();
        this.deleteAt = categories.getDeleteAt();
        this.creationDate = categories.getCreationDate();
        this.createdBy = categories.getCreatedBy();
        this.lastUpdateDate = categories.getLastUpdateDate();
        this.lastUpdatedBy = categories.getLastUpdatedBy();
    }

}
