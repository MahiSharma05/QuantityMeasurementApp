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
	public void onAuthenticationSuccess(HttpServletRequest request,
	                                    HttpServletResponse response,
	                                    Authentication auth)
	        throws java.io.IOException {

	    OAuth2User user = (OAuth2User) auth.getPrincipal();

	    String email = user.getAttribute("email");
	    String name  = user.getAttribute("name");
	    String avatar = user.getAttribute("picture"); 

	    String token = jwtUtil.generateToken(email);

	   
	    String redirectUrl = "http://localhost:4200/oauth2/callback"
	            + "?token=" + token
	            + "&email=" + email
	            + "&name=" + (name != null ? name : "")
	            + "&avatar=" + (avatar != null ? avatar : "");

	    response.sendRedirect(redirectUrl);
	}

}