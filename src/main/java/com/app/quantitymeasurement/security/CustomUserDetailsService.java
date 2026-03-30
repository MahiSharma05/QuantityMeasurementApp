package com.app.quantitymeasurement.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.quantitymeasurement.user.User;
import com.app.quantitymeasurement.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service

public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepo;

	public CustomUserDetailsService(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {

		User user = userRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

		return new CustomUserDetails(user);
	}
}