package com.dam_proyect.api_figth.controller;

import com.dam_proyect.api_figth.model.User;
import com.dam_proyect.api_figth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        userService.registerUser(user);
        return user;
    }

    @GetMapping("{username}")
    public User getUser(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

}
