package com.tradeplatform.tradeanalytics.controller;

import com.tradeplatform.tradeanalytics.model.User;
import com.tradeplatform.tradeanalytics.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        System.out.println("🚀 Received login request...");

        if (loginRequest == null || loginRequest.getEmail() == null || loginRequest.getPasswordHash() == null) {
            System.out.println("⚠️ Request body is null or missing required fields!");
            return ResponseEntity.badRequest().body("Invalid request payload");
        }

        System.out.println("✅ Email received: " + loginRequest.getEmail());

        Optional<User> user = userService.findByEmail(loginRequest.getEmail());

        System.out.println(user.get().getId());
        if (user.isPresent() && user.get().getPasswordHash().equals(loginRequest.getPasswordHash())) {
            System.out.println("🔓 Login successful for: " + loginRequest.getEmail());
            return ResponseEntity.ok(user.get());
        } else {
            System.out.println("❌ Invalid credentials for: " + loginRequest.getEmail());
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}