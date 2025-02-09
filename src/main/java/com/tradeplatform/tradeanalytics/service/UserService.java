package com.tradeplatform.tradeanalytics.service;


import com.tradeplatform.tradeanalytics.repository.UserRepository;
import com.tradeplatform.tradeanalytics.model.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user)
    {
        if (user.getUid() == null) {
            user.setUid(new String());
        }
        return userRepository.save(user);
    }
}
