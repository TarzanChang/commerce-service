package com.gtelant.commerce_service.mappers;

import com.gtelant.commerce_service.models.Segments;
import com.gtelant.commerce_service.requests.SegmentRequest;
import com.gtelant.commerce_service.responses.SegmentResponse;
import org.springframework.stereotype.Component;

@Component
public class SegmentMapper {
    public SegmentResponse toSegmentResponse(Segments segments){
        SegmentResponse dto = new SegmentResponse();
        dto.setSegmentId(segments.getSegmentId());
        dto.setSegmentName(segments.getSegmentName());
        dto.setDeleteAt(segments.getDeleteAt());
        dto.setCreationDate(segments.getCreationDate());
        dto.setCreatedBy(segments.getCreatedBy());
        dto.setLastUpdateDate(segments.getLastUpdateDate());
        dto.setLastUpdatedBy(segments.getLastUpdatedBy());
        return dto;
    }

    public Segments toSegment(SegmentRequest dto){
        Segments segments = new Segments();
        segments.setSegmentName(dto.getSegmentName());
        segments.setCreationDate(dto.getCreationDate());
        segments.setCreatedBy(dto.getCreatedBy());
        segments.setLastUpdateDate(dto.getLastUpdateDate());
        segments.setLastUpdatedBy(dto.getLastUpdatedBy());
        return segments;
    }
}
