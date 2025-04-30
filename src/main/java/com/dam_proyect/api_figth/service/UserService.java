package com.dam_proyect.api_figth.service;

import com.dam_proyect.api_figth.model.User;
import com.dam_proyect.api_figth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
