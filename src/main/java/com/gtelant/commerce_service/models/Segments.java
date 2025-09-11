package com.gtelant.commerce_service.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "segments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Segments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "segment_id")
    private int segmentId;
    @Column(name = "segment_name")
    private String segmentName;
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

    @OneToMany(mappedBy = "segments",fetch = FetchType.LAZY)
    private List<UserSegment> userSegmentList;
}
