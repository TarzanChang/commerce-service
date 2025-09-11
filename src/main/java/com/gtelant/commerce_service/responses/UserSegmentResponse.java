package com.gtelant.commerce_service.responses;

import lombok.Data;

@Data
public class UserSegmentResponse {
    private int userSegmentId;
    private int userId;
    private int segmentId;
    private String segmentName;
}
