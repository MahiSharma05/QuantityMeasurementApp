package com.user.userservice.service;

import com.user.userservice.dto.*;
import com.user.userservice.entity.*;
import com.user.userservice.exception.*;
import com.user.userservice.repository.UserRepository;
import com.user.userservice.security.JwtService;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Single source of truth for all auth logic.
 * Register → only create user
 * Login → generate JWT
 */
@Service
public class AuthService {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AuthenticationManager authManager, JwtService jwtService,
                       UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authManager    = authManager;
        this.jwtService     = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // FIXED REGISTER
    public String register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException(request.getEmail());
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .provider(AuthProvider.LOCAL)
                .build();

        userRepository.save(user);

        return "User registered successfully";
    }

    // LOGIN (GENERATES TOKEN)
    public AuthResponse login(LoginRequest request) {
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException();
        }

        String token = jwtService.generateToken(request.getEmail());

        return new AuthResponse(token, request.getEmail());
    }
}