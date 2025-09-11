package com.gtelant.commerce_service.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_segment",uniqueConstraints = {
        @UniqueConstraint(name = "uk1"
                ,columnNames = {"user_id", "segment_id"})}
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSegment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_Segment_id")
    private int userSegmentId;
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
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    private Users users;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "segment_id",referencedColumnName = "segment_id")
    private Segments segments;
}
