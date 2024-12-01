package com.sparta.msa_exam.auth.service;

import com.sparta.msa_exam.auth.dto.AuthSignUpRequest;
import com.sparta.msa_exam.auth.dto.AuthSignUpResponse;
import com.sparta.msa_exam.auth.model.User;
import com.sparta.msa_exam.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final UserRepository userRepository;

    @Transactional
    public AuthSignUpResponse signUp(AuthSignUpRequest request) {
        User user = userRepository.save(request.toUser());
        return new AuthSignUpResponse(user.getUsername());
    }
}
