package com.gtelant.commerce_service.services;

import com.gtelant.commerce_service.models.Segments;
import com.gtelant.commerce_service.models.UserSegment;
import com.gtelant.commerce_service.models.Users;
import com.gtelant.commerce_service.repositories.SegmentRepository;
import com.gtelant.commerce_service.repositories.UserRepository;
import com.gtelant.commerce_service.repositories.UserSegmentRepository;
import com.gtelant.commerce_service.requests.WhoColumnsRequest;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserSegmentRepository userSegmentRepository;
    private final SegmentRepository segmentRepository;

    @Autowired
    public UserService(UserRepository userRepository, UserSegmentRepository userSegmentRepository, SegmentRepository segmentRepository) {
        this.userRepository = userRepository;
        this.userSegmentRepository = userSegmentRepository;
        this.segmentRepository = segmentRepository;
    }


    public List<Users> getAllUsers() {
            return userRepository.findAll();
    }
    //聯集的寫法，即搜尋條件為 "or" 的寫法
//    public Page<Users> getAllUsers(String query, Boolean hasNewsletter, Integer segmentId, PageRequest pageRequest) {
//        if(query != null && !query.isEmpty()){
//            return userRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(query, query, pageRequest);
//        } else if (hasNewsletter != null) {
//            return userRepository.findByHasNewsletter(hasNewsletter,pageRequest);
//        } else if (segmentId != null) {
//            return userRepository.findByUserSegmentList_Segments_SegmentId(segmentId,pageRequest);
//        }
//        return userRepository.findAll(pageRequest);
//    }

    //交集的寫法 Specification
    public Page<Users> getAllUsers(String query, Boolean hasNewsletter, Integer segmentId, PageRequest pageRequest) {
        Specification<Users> spec = userSpecification(query,hasNewsletter,segmentId);
        return userRepository.findAll(spec, pageRequest);
    }

    private Specification<Users> userSpecification(String queryName, Boolean hasNewsletter, Integer segmentId) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            // if predicates.size() = 3 how many "AND"? => 2
            //if predicates.size() = 8  how many "AND"? => 7

            if(queryName != null && !queryName.isEmpty()) {
                predicates.add(criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder
                                        .lower(root.get("firstName")), "%"+ queryName.toLowerCase()+"%")
                        ,criteriaBuilder.like(criteriaBuilder
                                        .lower(root.get("lastName")), "%"+ queryName.toLowerCase()+"%")
                ));
            }
            if(hasNewsletter != null) {
                predicates.add(criteriaBuilder.equal(root.get("hasNewsletter"), hasNewsletter));
            }

            if(segmentId != null) {
                Join<Users , UserSegment> userUserSegmentJoin = root.join("userSegmentList");
                predicates.add(criteriaBuilder.equal(userUserSegmentJoin.get("segments").get("segmentId"), segmentId));

                //如果 userSegment有 屬性segmentId 則可以直接使用
                //predicates.add(criteriaBuilder.equal(userUserSegmentJoin.get("segmentId"), segmentId));

                //如果欲查詢Segment參數為字串（name）=> segmentName
                //predic ates.add(criteriaBuilder.equal(userUserSegmentJoin.get("segment").get("name"), segmentName)
            }

            Predicate[] predicateArray = predicates.toArray(new Predicate[0]);
            return criteriaBuilder.and(predicateArray);
        });
    }

    public Optional<Users> getUserById(int id) {
        return userRepository.findById(id);
    }

    public Users createUser(Users users) {
        return userRepository.save(users);
    }

    public Users updateUser(int id, Users users) {
        if (userRepository.existsById(id)) {
            users.setUserId(id);
            return userRepository.save(users);
        }
        return null;
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public void assignSegmentToUser(int id, int segmentId, WhoColumnsRequest whoColumnsRequest) {
        Optional<Segments> segment = segmentRepository.findById(segmentId);
        Optional<Users> user = userRepository.findById(id);

        if(user.isPresent() && segment.isPresent() ) {
            UserSegment userSegment = new UserSegment();
            userSegment.setSegments(segment.get());
            userSegment.setUsers(user.get());
            userSegment.setCreationDate(whoColumnsRequest.getCreationDate());
            userSegment.setCreatedBy(whoColumnsRequest.getCreatedBy());
            userSegment.setLastUpdateDate(whoColumnsRequest.getLastUpdateDate());
            userSegment.setLastUpdatedBy(whoColumnsRequest.getLastUpdatedBy());
            userSegmentRepository.save(userSegment);
        }
    }
}
