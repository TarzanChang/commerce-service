package com.gtelant.commerce_service.services;

import com.gtelant.commerce_service.models.Users;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    //負責 產生/解析 JWT(Json web token)

    private Key getKey() {
        byte[] keyByte = Decoders.BASE64.decode("dGlueXNhbWVoYW5kc29tZXlvdW5nY2FsbHJlY29yZGdpZnRpbnZlbnRlZHdpdGhvdXQ=");
        return Keys.hmacShaKeyFor(keyByte);
    }

    //從 JWT 中解析出 Email
    public String getEmailFromToken(String jwtToken) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody()
                .getSubject();
    }

    //組 Token
    public String generateToken(Users users){
        //方法1
//        JwtBuilder jwts = new JwtBuilder();
//        jwts.setSubject(user.getUsername());
//        jwts.setIssuedAt(new Date(System.currentTimeMillis()));
//        return jwts.compact();

        //方法2
        return Jwts.builder()
                //唯一的使用者名稱
                .setSubject(users.getEmail())
                //發行時間
                .setIssuedAt(new Date(System.currentTimeMillis()))
                //過期時間
                .setExpiration(new Date(System.currentTimeMillis() + 28800000)) //八小時候過期
                //對 jwt 進行簽名
                .signWith(getKey(), SignatureAlgorithm.HS256)
                //組合成字串
                .compact();
    }
}
