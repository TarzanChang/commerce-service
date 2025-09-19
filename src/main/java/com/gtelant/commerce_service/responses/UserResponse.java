package com.gtelant.commerce_service.responses;

import com.gtelant.commerce_service.models.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthday;
    private String address;
    private String city;
    private String state;
    private String zipcode;
    private String role;
    private boolean hasNewsletter;
    private LocalDateTime deleteAt;
    private LocalDateTime creationDate;
    private String createdBy;
    private LocalDateTime lastUpdateDate;
    private String lastUpdatedBy;
    private List<UserSegmentResponse> userSegmentResponses;

    public UserResponse(Users users) {
        this.userId = users.getUserId();
        this.firstName = users.getFirstName();
        this.lastName = users.getLastName();
        this.email = users.getEmail();
        this.birthday = users.getBirthday();
        this.address = users.getAddress();
        this.city = users.getCity();
        this.state = users.getState();
        this.zipcode = users.getZipcode();
        this.deleteAt = users.getDeleteAt();
        this.creationDate = users.getCreationDate();
        this.createdBy = users.getCreatedBy();
        this.lastUpdateDate = users.getLastUpdateDate();
        this.lastUpdatedBy = users.getLastUpdatedBy();
    }
}
