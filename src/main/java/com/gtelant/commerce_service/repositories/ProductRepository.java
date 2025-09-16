package com.gtelant.commerce_service.repositories;

import com.gtelant.commerce_service.models.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductRepository extends JpaRepository<Products,Integer>, JpaSpecificationExecutor<Products> {
}
