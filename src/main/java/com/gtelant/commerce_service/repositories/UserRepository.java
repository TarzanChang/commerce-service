package com.gtelant.commerce_service.repositories;

import com.gtelant.commerce_service.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


public interface UserRepository extends JpaRepository<Users, Integer>, JpaSpecificationExecutor<Users> {

    //聯集的寫法，即搜尋條件為 "or" 的寫法
//    Page<Users> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName,String lastName, Pageable pageable);
//    List<Users> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);
//
//    Page<Users> findByHasNewsletter(boolean hasNewsletter, Pageable pageable);
//    List<Users> findByHasNewsletter(boolean hasNewsletter);
//
//    Page<Users> findByUserSegmentList_Segments_SegmentId(int segmentId, Pageable pageable);
//    List<Users> findByUserSegmentList_Segments_SegmentId(int segmentId);

}
