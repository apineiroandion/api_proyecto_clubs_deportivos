package com.dam_proyect.api_figth.controller;

import com.dam_proyect.api_figth.dto.LoginRequest;
import com.dam_proyect.api_figth.dto.RegisterRequest;
import com.dam_proyect.api_figth.dto.ResponseModel;
import com.dam_proyect.api_figth.model.User;
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
    public ResponseEntity<ResponseModel<String>> login(@RequestBody @Validated LoginRequest request) {
        boolean loginSuccess = authService.login(request.getUsername(), request.getPassword());

        if (loginSuccess) {
            return ResponseEntity.ok(new ResponseModel<>(
                    "Login correcto", true, null
            ));
        } else {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseModel<>(
                            "Usuario o contraseña incorrectos", false, null
                    ));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseModel<String>> register(@RequestBody RegisterRequest request) {
        try{
            boolean isRegistered = authService.registerUser(request);
            if (isRegistered) {
                ResponseModel<String> response = new ResponseModel<>(
                        "Registro exitoso", true, null);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            } else {
                ResponseModel<String> response = new ResponseModel<>(
                        "El usuario ya existe", false, null);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            ResponseModel<String> response = new ResponseModel<>(
                    "Error al registrar el usuario: " + e.getMessage(), false, null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam("token") String token) {
        return authService.confirmToken(token);
    }
}
