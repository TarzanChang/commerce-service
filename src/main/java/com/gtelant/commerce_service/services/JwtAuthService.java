package com.gtelant.commerce_service.services;

import com.gtelant.commerce_service.models.Users;
import com.gtelant.commerce_service.repositories.UserRepository;
import com.gtelant.commerce_service.requests.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtAuthService {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public String register(Users users){
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        userRepository.save(users);
        return jwtService.generateToken(users);
    }

    public String login(LoginRequest request) {
        Optional<Users> usersOptional = userRepository.findByEmail(request.getEmail());
        if(usersOptional.isPresent()){
            Users users = usersOptional.get();
            if (passwordEncoder.matches(request.getPassword(),users.getPassword())){
                return jwtService.generateToken(users);
            }
        }
        throw new RuntimeException("無效的憑證!!");
    }
}
