package com.app.quantitymeasurement.response;

import lombok.Data;

@Data
public class AuthResponse {
	private String token;

	public AuthResponse(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

}