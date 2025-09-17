package com.gtelant.commerce_service.services;

import com.gtelant.commerce_service.models.Categories;
import com.gtelant.commerce_service.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Categories> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Page<Categories> getAllCategories(PageRequest pageRequest) {
        return categoryRepository.findAll(pageRequest);
    }

    public Categories getCategoryById(int id) {
        Optional<Categories> categoriesOptional = categoryRepository.findById(id);
        return categoriesOptional.orElseThrow(() -> new RuntimeException("Category not found."));
    }

    public Categories createCategory(Categories categories) {
        return categoryRepository.save(categories);
    }

    public Categories updateCategory(int id, Categories categories) {
        if (categoryRepository.existsById(id)) {
            categories.setCategoryId(id);
            return categoryRepository.save(categories);
        }
        return null;
    }

    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }
}
