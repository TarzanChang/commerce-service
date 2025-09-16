package com.gtelant.commerce_service.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;
    @Column(name = "product_name",nullable = false)
    private String productName;
    @Column(name = "image_url",nullable = false)
    private String imageUrl ;
    @Column(name = "thumbnail_url",nullable = false)
    private String thumbnailUrl;
    @Column(name = "width",nullable = false)
    private double width;
    @Column(name = "height",nullable = false)
    private double height;
    @Column(name = "price",nullable = false)
    private BigDecimal price;
    @Column(name = "stock",nullable = false)
    private int stock;
    @Column(name = "sales",nullable = false)
    private int sales;
    @Column(name = "description",nullable = false)
    private String description;
    @Column(name = "delete_at")
    private LocalDateTime deleteAt;
    @CreationTimestamp
    @Column(name = "creation_date",nullable = false)
    private LocalDateTime creationDate;
    @Column(name = "created_by",nullable = false)
    private String createdBy;
    @UpdateTimestamp
    @Column(name = "last_update_date",nullable = false)
    private LocalDateTime lastUpdateDate;
    @Column(name = "last_updated_by",nullable = false)
    private String lastUpdatedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",referencedColumnName = "category_id")
    private Categories categories;
}
