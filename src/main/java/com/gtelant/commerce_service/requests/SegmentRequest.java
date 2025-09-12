package com.gtelant.commerce_service.requests;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SegmentRequest {
    private String segmentName;
    private LocalDateTime creationDate;
    private String createdBy;
    private LocalDateTime lastUpdateDate;
    private String lastUpdatedBy;
}
