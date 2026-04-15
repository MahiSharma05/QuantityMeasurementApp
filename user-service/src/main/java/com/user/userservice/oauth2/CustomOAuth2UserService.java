package com.user.userservice.oauth2;

import com.user.userservice.entity.AuthProvider;
import com.user.userservice.entity.User;
import com.user.userservice.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.*;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(request);
        String email      = oAuth2User.getAttribute("email");
        String providerId = oAuth2User.getAttribute("sub");

        userRepository.findByEmail(email).orElseGet(() ->
                userRepository.save(User.builder()
                        .email(email)
                        .provider(AuthProvider.GOOGLE)
                        .providerId(providerId)
                        .build())
        );
        return oAuth2User;
    }
}
