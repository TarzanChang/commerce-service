package com.gtelant.commerce_service.controllers;

import com.gtelant.commerce_service.mappers.UserMapper;
import com.gtelant.commerce_service.responses.UserResponse;
import com.gtelant.commerce_service.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "User management APIs")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;

    }

    @Operation(summary = "Get all users", description = "Returns a list of all users")
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){
//            @RequestParam(defaultValue = "") String query,
//            @RequestParam(required = false) Boolean hasNewsletter,
//            @RequestParam(required = false) Integer segmentId){

        return ResponseEntity.ok(userService.getAllUsers().stream()
                .map(userMapper::toUserResponse)
                .toList());
    }

    @Operation(summary = "Get all users pagination", description = "Returns a page of users")
    @GetMapping("/page")
    public Page<UserResponse> getAllUsersPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "") String query,
            @RequestParam(required = false) Boolean hasNewsletter,
            @RequestParam(required = false) Integer segmentId
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return userService.getAllUsers(pageRequest).map(userMapper::toUserResponse);
    }
}
