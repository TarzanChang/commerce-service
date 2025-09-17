package com.gtelant.commerce_service.mappers;

import com.gtelant.commerce_service.models.Categories;
import com.gtelant.commerce_service.requests.CategoryRequest;
import com.gtelant.commerce_service.responses.CategoryResponse;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryResponse toCategoryResponse(Categories categories){
        CategoryResponse dto = new CategoryResponse();
        dto.setCategoryId(categories.getCategoryId());
        dto.setCategoryName(categories.getCategoryName());
        dto.setDeleteAt(categories.getDeleteAt());
        dto.setCreationDate(categories.getCreationDate());
        dto.setCreatedBy(categories.getCreatedBy());
        dto.setLastUpdateDate(categories.getLastUpdateDate());
        dto.setLastUpdatedBy(categories.getLastUpdatedBy());
        return dto;
    }

    public Categories toCategory(CategoryRequest dto){
        Categories categories = new Categories();
        categories.setCategoryName(dto.getCategoryName());
        categories.setCreationDate(dto.getCreationDate());
        categories.setCreatedBy(dto.getCreatedBy());
        categories.setLastUpdateDate(dto.getLastUpdateDate());
        categories.setLastUpdatedBy(dto.getLastUpdatedBy());
        return categories;
    }
}
