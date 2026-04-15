package com.user.userservice.controller;

import com.user.userservice.dto.*;
import com.user.userservice.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 * Thin HTTP layer — all logic in AuthService.
 * Register → only create user
 * Login → generate JWT
 */
@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Register and Login")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // REGISTER API
    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {
        String response = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // LOGIN API
    @PostMapping("/login")
    @Operation(summary = "Login with email/password — returns JWT")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}