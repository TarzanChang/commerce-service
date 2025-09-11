package com.gtelant.commerce_service.repositories;

import com.gtelant.commerce_service.models.Segments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SegmentRepository extends JpaRepository<Segments,Integer> {
}
