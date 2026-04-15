package com.user.userservice.dto;

import com.user.userservice.entity.AuthProvider;
import com.user.userservice.entity.User;
import lombok.*;
import com.user.userservice.dto.UserResponse;
/**
 * Safe DTO for returning user info — password never exposed.
 */
@Data @AllArgsConstructor @NoArgsConstructor
public class UserResponse {
    private Long id;
    private String email;
    private AuthProvider provider;

    public static UserResponse from(User user) {
        return new UserResponse(user.getId(), user.getEmail(), user.getProvider());
    }
}
