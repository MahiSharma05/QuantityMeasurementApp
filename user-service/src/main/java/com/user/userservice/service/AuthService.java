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

 * Handles all authentication logic:
 * * Register user
 * * Login user (JWT generation)
 */
@Service
public class AuthService {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AuthenticationManager authManager,
                       JwtService jwtService,
                       UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ================= REGISTER =================

    public String register(RegisterRequest request) {

        // Check if user already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException(request.getEmail());
        }

        // Create new user
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .provider(AuthProvider.LOCAL)
                .build();

        userRepository.save(user);

        return "User registered successfully";
    }

    // ================= LOGIN =================

    public AuthResponse login(LoginRequest request) {

        try {
            // Authenticate user credentials
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException();
        }

        // Generate JWT token
        String token = jwtService.generateToken(request.getEmail());

        // Return response
        return new AuthResponse(
                token,
                request.getEmail()
        );

    }

    // ================= OPTIONAL (FOR GOOGLE USERS) =================
    // You can use this later if you want unified handling

    public AuthResponse loginOAuthUser(String email) {

        // If user doesn't exist, create it
        User user = userRepository.findByEmail(email)
                .orElseGet(() -> {
                    User newUser = User.builder()
                            .email(email)
                            .password("") // No password for OAuth users
                            .provider(AuthProvider.GOOGLE)
                            .build();
                    return userRepository.save(newUser);
                });

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(token, user.getEmail());

    }
}
