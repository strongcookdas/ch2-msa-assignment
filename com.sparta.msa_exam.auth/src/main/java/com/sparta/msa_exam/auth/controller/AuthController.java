package com.sparta.msa_exam.auth.controller;

import com.sparta.msa_exam.auth.dto.AuthSignUpRequest;
import com.sparta.msa_exam.auth.dto.AuthSignUpResponse;
import com.sparta.msa_exam.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    @PostMapping("/sign-up")
    public ResponseEntity<AuthSignUpResponse> signUp(@RequestBody AuthSignUpRequest request){
        AuthSignUpResponse response = authService.signUp(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
