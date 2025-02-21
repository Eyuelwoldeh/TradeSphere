package com.tradeplatform.tradeanalytics.service;

import com.tradeplatform.tradeanalytics.repository.UserRepository;
import com.tradeplatform.tradeanalytics.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // Existing methods remain the same
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findByUid(String uid) {
        return userRepository.findByUid(uid);
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        if (user.getUid() == null || user.getUid().isEmpty()) {
            user.setUid(UUID.randomUUID().toString());
        }
        return userRepository.save(user);
    }

    // Add this method to implement UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username); // Using email as username
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }

        // Convert your User to Spring Security's UserDetails
        return org.springframework.security.core.userdetails.User
                .withUsername(user.get().getEmail())
                .password(user.get().getPasswordHash())
                .authorities(new ArrayList<>()) // Add roles/authorities if you have them
                .build();
    }
}