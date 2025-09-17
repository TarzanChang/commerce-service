package com.gtelant.commerce_service.repositories;

import com.gtelant.commerce_service.models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Categories,Integer> {
}
