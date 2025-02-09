package com.tradeplatform.tradeanalytics.service;

import com.tradeplatform.tradeanalytics.repository.UserRepository;
import com.tradeplatform.tradeanalytics.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // ✅ Find user by email (no changes)
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // ✅ Find user by UID
    public Optional<User> findByUid(String uid) {
        return userRepository.findByUid(uid);
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    // ✅ Generate UID properly for new users
    public User createUser(User user) {
        if (user.getUid() == null || user.getUid().isEmpty()) {
            user.setUid(UUID.randomUUID().toString());  // Generates a unique string UID
        }
        return userRepository.save(user);
    }
}