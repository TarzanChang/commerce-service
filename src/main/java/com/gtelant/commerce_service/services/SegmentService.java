package com.gtelant.commerce_service.services;

import com.gtelant.commerce_service.models.Segments;
import com.gtelant.commerce_service.repositories.SegmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SegmentService {
    private final SegmentRepository segmentRepository;

    @Autowired
    public SegmentService(SegmentRepository segmentRepository) {
        this.segmentRepository = segmentRepository;
    }

    public List<Segments> getAllSegments() {
        return segmentRepository.findAll();
    }

    public Page<Segments> getAllSegments(PageRequest pageRequest) {
        return segmentRepository.findAll(pageRequest);
    }

    public Optional<Segments> getSegmentById(int id) {
        return segmentRepository.findById(id);
    }

    public Segments createSegment(Segments segments) {
        return segmentRepository.save(segments);
    }

    public Segments updateSegment(int id, Segments segments) {
        if (segmentRepository.existsById(id)) {
            segments.setSegmentId(id);
            return segmentRepository.save(segments);
        }
        return null;
    }

    public void deleteSegment(int id) {
        segmentRepository.deleteById(id);
    }
}