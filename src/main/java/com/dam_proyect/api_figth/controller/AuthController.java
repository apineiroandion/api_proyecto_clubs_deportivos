package com.dam_proyect.api_figth.controller;

import com.dam_proyect.api_figth.dto.*;
import com.dam_proyect.api_figth.security.userdetails.CustomUserDetails;
import com.dam_proyect.api_figth.security.userdetails.CustomUserDetailsService;
import com.dam_proyect.api_figth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.dam_proyect.api_figth.security.jwt.JWTUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;
    private final AuthenticationManager authManager;
    private final JWTUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    public AuthController(AuthenticationManager authManager, JWTUtil jwtUtil, CustomUserDetailsService userDetailsService) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody AuthRequest request) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        String token = jwtUtil.generateToken(userDetails.getUsername()); // o con claims

        return ResponseEntity.ok(new AuthResponse(token));
    }

    //    @PostMapping("/login")
//    public ResponseEntity<ResponseBaseDto<String>> login(@RequestBody @Validated LoginRequestDto request) {
//        boolean loginSuccess = authService.login(request.getUsername(), request.getPassword());
//
//        if (loginSuccess) {
//            return ResponseEntity.ok(new ResponseBaseDto<>(
//                    "Login correcto", true, null
//            ));
//        } else {
//            return ResponseEntity
//                    .status(HttpStatus.UNAUTHORIZED)
//                    .body(new ResponseBaseDto<>(
//                            "Usuario o contraseña incorrectos", false, null
//                    ));
//        }
//    }


}


