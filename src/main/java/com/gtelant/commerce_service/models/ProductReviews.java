package com.gtelant.commerce_service.models;

import com.gtelant.commerce_service.enums.ReviewStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "product_reviews")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductReviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pt_review_id")
    private int ptReviewId;
    @Column(name = "review_date")
    private LocalDate reviewDate;
    @Column(name = "rating")
    private int rating;
    @Column(name = "comment")
    private String comment;
    @Column(name = "review_status")
    private ReviewStatus reviewStatus;
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id",referencedColumnName = "product_id")
    private Products products;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",referencedColumnName = "user_id",nullable = false)
    private Users users;
}
