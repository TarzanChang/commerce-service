package com.gtelant.commerce_service.configs;

import com.gtelant.commerce_service.models.Users;
import com.gtelant.commerce_service.repositories.UserRepository;
import com.gtelant.commerce_service.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    //過濾請求/賦予權限
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
//        System.out.println("authHeader "+ authHeader);
        // 檢查Authorization格式是否正確
        //authHeader -> Bearer XXXXXXXX
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            //該次請求過濾器結束生命週期=>將請求繼續往下傳遞...
            filterChain.doFilter(request, response);
            return;
        }
        try{
            // 若開頭格式（Bearer...）正確，則擷取第七字元開始的字串（實際jwť）
            String jwtToken = authHeader.substring(7);
            String email = jwtService.getEmailFromToken(jwtToken);

            if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // db裡面找到對應的username
                Optional<Users> users = userRepository.findByEmail(email);
                // todo 驗證 token是否過期或是無效
                if (users.isPresent()) {
                    //*****權限
    //                System.out.println("user.isPresent() "+ user);
                    //*** 若使用Spring Security (library)必須包含 授權 (Authorization)邏輯->「該用戶能做什麼？」***
                    List<? extends GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(users.get().getRole()));
                    System.out.println("authorities " + authorities);
                    //該token並非jwt token，而是Spring Security內部使用的token(包含user & authorities)
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(users.get(), null, authorities);
                    // 將 內部使用的token 投進 Spring Security 認證箱（SecurityContextHolder）
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }catch (Exception e){
            throw new ServletException(e);
        }
        filterChain.doFilter(request, response);
    }
}
