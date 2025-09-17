package com.gtelant.commerce_service.controllers;

import com.gtelant.commerce_service.mappers.SegmentMapper;
import com.gtelant.commerce_service.models.Segments;
import com.gtelant.commerce_service.requests.SegmentRequest;
import com.gtelant.commerce_service.responses.SegmentResponse;
import com.gtelant.commerce_service.services.SegmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/segments")
@Tag(name = "Segments", description = "Segment management APIs.")
public class SegmentController {
    private final SegmentService segmentService;
    private final SegmentMapper segmentMapper;

    @Autowired
    public SegmentController(SegmentService segmentService, SegmentMapper segmentMapper) {
        this.segmentService = segmentService;
        this.segmentMapper = segmentMapper;
    }

    @Operation(summary = "Get all segments", description = "Returns a list of all segments")
    @GetMapping
    public ResponseEntity<List<SegmentResponse>> getAllSegments(){
        return ResponseEntity.ok(segmentService.getAllSegments().stream()
                .map(segmentMapper::toSegmentResponse)
                .toList());
    }

    @Operation(summary = "Get segment by ID.",description = "Returns a single segment by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Successfully retrieved Segment."),
            @ApiResponse(responseCode = "404",description = "Segment not found.")})
    @GetMapping("/{id}")
    public ResponseEntity<SegmentResponse> getSegmentById(@PathVariable int id){
        Optional<Segments> segments = segmentService.getSegmentById(id);
        if (segments.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(segmentMapper.toSegmentResponse(segments.get()));
    }

    @Operation(summary = "Create a new segment.",description = "Creates a new segment and returns the created segment.")
    @PostMapping
    public ResponseEntity<SegmentResponse> createSegment(@RequestBody SegmentRequest segmentRequest){
        Segments segments = segmentMapper.toSegment(segmentRequest);
        Segments createdSegment = segmentService.createSegment(segments);
        return ResponseEntity.ok(segmentMapper.toSegmentResponse(createdSegment));
    }

    @Operation(summary = "Update an existing segmemt.",description = "Updates an existing segmemt by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Successfully retrieved segmemt."),
            @ApiResponse(responseCode = "404",description = "Segmemt not found.")
    })
    @PutMapping("/{id}")
    public ResponseEntity<SegmentResponse> updateSegment(@PathVariable int id, @RequestBody SegmentRequest segmentRequest){
        Segments segments = segmentMapper.toSegment(segmentRequest);
        Segments updatedSegment = segmentService.updateSegment(id,segments);
        if(updatedSegment == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(segmentMapper.toSegmentResponse(updatedSegment));
    }

    @Operation(summary = "Delete a segment.",description = "Deletes a segment by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "Successfully deleted segment."),
            @ApiResponse(responseCode = "404",description = "segment not found.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSegment(@PathVariable int id){
        segmentService.deleteSegment(id);
        return ResponseEntity.noContent().build();
    }
}
