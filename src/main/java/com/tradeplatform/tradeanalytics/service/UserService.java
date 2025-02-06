package com.tradeplatform.tradeanalytics.service;


import com.tradeplatform.tradeanalytics.repository.UserRepository;
import com.tradeplatform.tradeanalytics.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> allUsers() {
        List<User> users = userRepository.findAll();
        System.out.println("Fetched users from DB: " + users); // Debugging log
        return users;
    }
}
