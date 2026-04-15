package com.user.userservice.dto;

import lombok.*;

/**
 * Returned after login/register. NEVER contains the password.
 */
@Data @AllArgsConstructor @NoArgsConstructor
public class AuthResponse {
    private String token;
    private String email;
    private String tokenType = "Bearer";

    public AuthResponse(String token, String email) {
        this.token = token;
        this.email = email;
    }
}
