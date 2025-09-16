package com.gtelant.commerce_service.responses;

import com.gtelant.commerce_service.models.Users;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserSegmentResponse {
    private int userSegmentId;
    private int userId;
    private int segmentId;
    private String segmentName;
    private LocalDateTime deleteAt;
    private LocalDateTime creationDate;
    private String createdBy;
    private LocalDateTime lastUpdateDate;
    private String lastUpdatedBy;
}
