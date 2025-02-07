package com.tradeplatform.tradeanalytics.service;


import com.tradeplatform.tradeanalytics.repository.UserRepository;
import com.tradeplatform.tradeanalytics.model.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user)
    {
        if (user.getId() == null) {
            user.setId(new ObjectId());
        }
        return userRepository.save(user);
    }
}
