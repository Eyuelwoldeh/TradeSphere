package com.tradeplatform.tradeanalytics.controller;


import com.tradeplatform.tradeanalytics.dto.UserDTO;
import com.tradeplatform.tradeanalytics.model.User;
import com.tradeplatform.tradeanalytics.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@AuthenticationPrincipal UserDetails userDetails) {
        // Log the userDetails object to see if it's being passed correctly
        System.out.println("Received user details: " + userDetails);

        if (userDetails == null) {
            // Log if userDetails is null
            System.out.println("UserDetails is null, returning Unauthorized.");
            return ResponseEntity.status(401).body("Unauthorized");
        }

        // Find user in MongoDB
        Optional<User> user = userService.findByEmail(userDetails.getUsername());
        System.out.println("Looking up user with email: " + userDetails.getUsername());

        if (!user.isPresent()) {
            // Log when the user is not found in the database
            System.out.println("User not found with email: " + userDetails.getUsername());
            return ResponseEntity.status(404).body("User not found");
        }

        // Log the user details before sending to the client
        System.out.println("User found: " + user.get());

        UserDTO userDTO = new UserDTO(user.get().getUid(), user.get().getEmail(), user.get().getName());

        // Log the DTO that will be returned
        System.out.println("Returning userDTO: " + userDTO);

        // Send user info (without password)
        return ResponseEntity.ok(userDTO);
    }

    public static Long generateID() {
        return UUID.randomUUID().getLeastSignificantBits() & Long.MAX_VALUE;
    }

    @PostMapping("/create_user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
}
