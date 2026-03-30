package com.app.quantitymeasurement.oauth2;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.app.quantitymeasurement.security.JwtService;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private final JwtService jwtUtil;

	public OAuth2SuccessHandler(JwtService jwtUtil) {
		super();
		this.jwtUtil = jwtUtil;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, java.io.IOException {

		OAuth2User user = (OAuth2User) auth.getPrincipal();

		String username = user.getAttribute("email");
		String token = jwtUtil.generateToken(username);

		response.getWriter().write(token);
	}
}