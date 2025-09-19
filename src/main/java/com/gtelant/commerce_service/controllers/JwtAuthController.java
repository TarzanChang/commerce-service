package com.gtelant.commerce_service.controllers;

import com.gtelant.commerce_service.mappers.UserMapper;
import com.gtelant.commerce_service.models.Users;
import com.gtelant.commerce_service.requests.LoginRequest;
import com.gtelant.commerce_service.requests.UserRequest;
import com.gtelant.commerce_service.responses.AuthResponse;
import com.gtelant.commerce_service.services.JwtAuthService;
import com.gtelant.commerce_service.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jwt")
@Tag(name = "JWT驗證", description = "提供使用者登入註冊")
public class JwtAuthController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtAuthService jwtAuthService;

    @Operation(summary = "註冊用戶",description = "1.username 不得重複。 <br/>" +
            "2.密碼必須 8 個字元以上。 <br/>" +
            "3.必須提供用戶角色，並以 ROLE_開頭(ROLE_USER)。" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "操作成功"),
            @ApiResponse(responseCode = "401", description = "資料格式不正確"),
            @ApiResponse(responseCode = "403", description = "權限不符")
    })
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserRequest userRequest){
        Users users = userMapper.toUser(userRequest);
        String token = jwtAuthService.register(users);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @Operation(summary = "jwt 登入用戶",description = "返回 Token")
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        String token = jwtAuthService.login(request);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
