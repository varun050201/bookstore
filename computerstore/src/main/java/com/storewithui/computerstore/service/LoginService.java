package com.storewithui.computerstore.service;

import com.storewithui.computerstore.entity.User;
import com.storewithui.computerstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UserRepository userRepository;

    @Autowired
    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        // In a real application, check if the password matches and is hashed
        return user != null && user.getPassword().equals(password);
    }
}
