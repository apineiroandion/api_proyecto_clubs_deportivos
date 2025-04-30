package com.dam_proyect.api_figth.controller;

import com.dam_proyect.api_figth.dto.LoginRequest;
import com.dam_proyect.api_figth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody @Validated LoginRequest request) {
        if (authService.login(request.getUsername(), request.getPassword())) {
            return "Login correcto";
        } else {
            return "Usuario o contraseña incorrectos";
        }
    }
}
