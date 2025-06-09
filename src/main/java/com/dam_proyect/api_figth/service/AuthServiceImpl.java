package com.dam_proyect.api_figth.service;

import com.dam_proyect.api_figth.model.User;
import com.dam_proyect.api_figth.repository.UserRepository;
import com.dam_proyect.api_figth.repository.VerificationTokenRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository tokenRepository;

    public AuthServiceImpl(UserRepository userRepository, VerificationTokenRepository tokenRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) throw new RuntimeException("Usuario no encontrado");

        if (!user.isEnabled()) throw new RuntimeException("La cuenta no está activada");

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return true;
    }
}
