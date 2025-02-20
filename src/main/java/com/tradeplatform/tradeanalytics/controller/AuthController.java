package com.tradeplatform.tradeanalytics.controller;

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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {

        if (loginRequest == null || loginRequest.getEmail() == null || loginRequest.getPasswordHash() == null) {
            return ResponseEntity.badRequest().body("Invalid request payload");
        }

        Optional<User> user = userService.findByEmail(loginRequest.getEmail());

        if (user.isPresent()) {  // ✅ Only call .get() if the user exists
            if (user.get().getPasswordHash().equals(loginRequest.getPasswordHash())) {
                Map<String, User> tokenMap = new HashMap<>();
                tokenMap.put("token", user.get());
                return ResponseEntity.ok(tokenMap);
            } else {
                return ResponseEntity.status(401).body("Invalid credentials");
            }
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}