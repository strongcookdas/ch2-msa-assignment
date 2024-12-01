package com.sparta.msa_exam.auth.service;

import com.sparta.msa_exam.auth.dto.AuthSignInResponse;
import com.sparta.msa_exam.auth.dto.AuthSignUpRequest;
import com.sparta.msa_exam.auth.dto.AuthSignUpResponse;
import com.sparta.msa_exam.auth.dto.AuthSignInRequest;
import com.sparta.msa_exam.auth.model.User;
import com.sparta.msa_exam.auth.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final SecretKey secretKey;

    @Value("${spring.application.name}")
    private String issuer;

    @Value("${service.jwt.access-expiration}")
    private Long accessExpiration;

    public AuthService(@Value("${service.jwt.secret-key}") String secretKey,
                       UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public AuthSignUpResponse signUp(AuthSignUpRequest request) {
        User user = userRepository.save(request.toUser(passwordEncoder));
        return new AuthSignUpResponse(user.getUsername());
    }

    public AuthSignInResponse signIn(AuthSignInRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid user ID or password");
        }

        String token = createAccessToken(user.getId().toString());
        return new AuthSignInResponse(token);
    }


    public String createAccessToken(String userId) {
        return Jwts.builder()
                .claim("user_id", userId)
                .issuer(issuer)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + accessExpiration))
                .signWith(secretKey)
                .compact();
    }

}
