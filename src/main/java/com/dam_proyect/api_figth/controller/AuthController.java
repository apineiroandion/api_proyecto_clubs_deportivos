package com.dam_proyect.api_figth.controller;

import com.dam_proyect.api_figth.dto.LoginRequestDto;
import com.dam_proyect.api_figth.dto.RegisterRequestDto;
import com.dam_proyect.api_figth.dto.ResponseBaseDto;
import com.dam_proyect.api_figth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ResponseBaseDto<String>> login(@RequestBody @Validated LoginRequestDto request) {
        boolean loginSuccess = authService.login(request.getUsername(), request.getPassword());

        if (loginSuccess) {
            return ResponseEntity.ok(new ResponseBaseDto<>(
                    "Login correcto", true, null
            ));
        } else {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseBaseDto<>(
                            "Usuario o contraseña incorrectos", false, null
                    ));
        }
    }
}
