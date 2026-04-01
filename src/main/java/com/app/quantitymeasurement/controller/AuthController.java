package com.app.quantitymeasurement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.app.quantitymeasurement.repository.UserRepository;
import com.app.quantitymeasurement.security.JwtService;
import com.app.quantitymeasurement.user.User;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private JwtService jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder; 

    //  REGISTER (ENCRYPT PASSWORD)
    @PostMapping("/register")
    public User register(@RequestBody User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword())); 

        return repo.save(user);
    }

    // LOGIN (MATCH ENCRYPTED PASSWORD)
    @PostMapping("/login")
    public String login(@RequestBody User user) {

        User dbUser = repo.findByEmail(user.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
            return jwtUtil.generateToken(user.getEmail());
        }

        throw new RuntimeException("Invalid credentials");
    }
}