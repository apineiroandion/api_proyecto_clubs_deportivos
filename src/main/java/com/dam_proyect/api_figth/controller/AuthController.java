package com.dam_proyect.api_figth.controller;

import com.dam_proyect.api_figth.dto.*;
import com.dam_proyect.api_figth.security.userdetails.CustomUserDetails;
import com.dam_proyect.api_figth.security.userdetails.CustomUserDetailsService;
import com.dam_proyect.api_figth.service.AuthServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.dam_proyect.api_figth.security.jwt.JWTUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthServiceImpl authServiceImpl;
    private final AuthenticationManager authManager;
    private final JWTUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    public AuthController(AuthenticationManager authManager, JWTUtil jwtUtil, CustomUserDetailsService userDetailsService) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody AuthRequestDto request) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        String token = jwtUtil.generateToken(userDetails.getId());

        return ResponseEntity.ok(new AuthResponseDto(token));
    }

}


