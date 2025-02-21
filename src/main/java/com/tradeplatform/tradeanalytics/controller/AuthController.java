package com.tradeplatform.tradeanalytics.controller;

import com.tradeplatform.tradeanalytics.Util.JwtTokenUtil;
import com.tradeplatform.tradeanalytics.model.User;
import com.tradeplatform.tradeanalytics.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        if (loginRequest == null || loginRequest.getEmail() == null || loginRequest.getPasswordHash() == null) {
            return ResponseEntity.badRequest().body("Invalid request payload");
        }

        Optional<User> user = userService.findByEmail(loginRequest.getEmail());

        if (user.isPresent() && user.get().getPasswordHash().equals(loginRequest.getPasswordHash())) {
            // Generate JWT token
            String token = jwtTokenUtil.generateToken(user.get());

            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("email", user.get().getEmail());

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}