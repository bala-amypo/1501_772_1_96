package com.example.demo.service.impl;

import com.example.demo.repository.UserAccountRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.AuthService;
import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserAccountRepository userRepo;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtProvider;

    public AuthServiceImpl(
            UserAccountRepository userRepo,
            BCryptPasswordEncoder passwordEncoder,
            JwtTokenProvider jwtProvider
    ) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public AuthResponse authenticate(AuthRequest request) {
        String token = jwtProvider.generateToken(request.getUsername());
        return new AuthResponse(token);
    }
}
