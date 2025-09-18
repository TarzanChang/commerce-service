package com.gtelant.commerce_service.services;

import com.gtelant.commerce_service.models.Categories;
import com.gtelant.commerce_service.models.Products;
import com.gtelant.commerce_service.models.Users;
import com.gtelant.commerce_service.repositories.ProductRepository;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Products> getAllProducts(){
        return productRepository.findAll();
    }

    public Page<Products> getAllProducts(String query,Integer categoryId, Integer stockFrom, Integer stockTo, PageRequest pageRequest){
        Specification<Products> spec = productsSpecification(query,categoryId,stockFrom,stockTo);
        return productRepository.findAll(spec, pageRequest);
    }

    private Specification<Products> productsSpecification(String queryName, Integer categoryId, Integer stockFrom, Integer stockTo) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(queryName != null && !queryName.isEmpty()){
                predicates.add(criteriaBuilder.like(criteriaBuilder
                        .lower(root.get("productName")),"%" + queryName.toLowerCase() +"%"));
            }
            if(stockFrom != null && stockTo == null){
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("stock"), stockFrom));
                }
            if(stockFrom != null && stockTo != null){
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("stock"), stockFrom));
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("stock"), stockTo));
            }
            if (categoryId != null){
                predicates.add(criteriaBuilder.equal(root.get("categories").get("categoryId"),categoryId));
            }
            Predicate[] predicateArray = predicates.toArray(new Predicate[0]);
            return criteriaBuilder.and(predicateArray);
            });
    }

    public Optional<Products> getProductById(int id) {
        return productRepository.findById(id);
    }

    public Products createProduct(Products products) {
        return productRepository.save(products);
    }

    public Products updatedProduct(int id, Products products) {
        if (productRepository.existsById(id)) {
            products.setProductId(id);
            return productRepository.save(products);
        }
        return null;
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

}
