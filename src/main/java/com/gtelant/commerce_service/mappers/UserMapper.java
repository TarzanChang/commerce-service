package com.gtelant.commerce_service.mappers;

import com.gtelant.commerce_service.models.UserSegment;
import com.gtelant.commerce_service.models.Users;
import com.gtelant.commerce_service.requests.UserRequest;
import com.gtelant.commerce_service.requests.WhoColumnsRequest;
import com.gtelant.commerce_service.responses.UserResponse;
import com.gtelant.commerce_service.responses.UserSegmentResponse;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserResponse toUserResponse(Users users) {
        UserResponse dto = new UserResponse();
        dto.setUserId(users.getUserId());
        dto.setFirstName(users.getFirstName());
        dto.setLastName(users.getLastName());
        dto.setEmail(users.getEmail());
        dto.setBirthday(users.getBirthday());
        dto.setAddress(users.getAddress());
        dto.setCity(users.getCity());
        dto.setZipcode(users.getZipcode());
        dto.setRole(users.getRole());
        dto.setHasNewsletter(users.isHasNewsletter());
        dto.setDeleteAt(users.getDeleteAt());
        dto.setCreatedBy(users.getCreatedBy());
        dto.setCreationDate(users.getCreationDate());
        dto.setLastUpdatedBy(users.getLastUpdatedBy());
        dto.setLastUpdateDate(users.getLastUpdateDate());
        if (users.getUserSegmentList() != null) {
            dto.setUserSegmentResponses(users.getUserSegmentList().stream()
                    .map(this::toUserSegmentResponse)
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    public Users toUser(UserRequest dto) {
        Users users = new Users();
        users.setFirstName(dto.getFirstName());
        users.setLastName(dto.getLastName());
        users.setEmail(dto.getEmail());
        users.setBirthday(dto.getBirthday());
        users.setAddress(dto.getAddress());
        users.setCity(dto.getCity());
        users.setZipcode(dto.getZipcode());
        users.setPassword(dto.getPassword());
        users.setRole(dto.getRole());
        users.setHasNewsletter(dto.isHasNewsletter());
        users.setCreatedBy(dto.getCreatedBy());
        users.setCreationDate(dto.getCreationDate());
        users.setLastUpdatedBy(dto.getLastUpdatedBy());
        users.setLastUpdateDate(dto.getLastUpdateDate());
        return users;
    }

    public UserSegmentResponse toUserSegmentResponse(UserSegment userSegment) {
        UserSegmentResponse dto = new UserSegmentResponse();
        dto.setUserSegmentId(userSegment.getUserSegmentId());
        dto.setUserId(userSegment.getUsers().getUserId());
        dto.setSegmentId(userSegment.getSegments().getSegmentId());
        dto.setCreationDate(userSegment.getCreationDate());
        dto.setCreatedBy(userSegment.getCreatedBy());
        dto.setLastUpdateDate(userSegment.getLastUpdateDate());
        dto.setLastUpdatedBy(userSegment.getLastUpdatedBy());
        return dto;
    }

    public UserSegment toUserSegment(WhoColumnsRequest dto){
        UserSegment userSegment = new UserSegment();
        userSegment.setCreatedBy(dto.getCreatedBy());
        userSegment.setCreationDate(dto.getCreationDate());
        userSegment.setLastUpdatedBy(dto.getLastUpdatedBy());
        userSegment.setLastUpdateDate(dto.getLastUpdateDate());
        return userSegment;
    }
}
