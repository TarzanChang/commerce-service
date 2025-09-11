package com.gtelant.commerce_service.repositories;

import com.gtelant.commerce_service.models.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UserRepository extends JpaRepository<Users, Integer> {
}
//public interface UserRepository extends JpaRepository<Users,Integer>, JpaSpecificationExecutor<Users> {
//    Page<Users> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firsName, String lastName, Pageable pageable);
//    List<Users> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firsName, String lastName);
//
//    Page<Users> findByHasNewsletter(boolean hasNewsletter, Pageable pageable);
//    List<Users> findByHasNewsletter(boolean hasNewsletter);
//
//    Page<Users> findByUserSegmentsSegmentId(int segmentId, Pageable pageable);
//    List<Users> findByUserSegmentsSegmentId(int segmentId);
//}
