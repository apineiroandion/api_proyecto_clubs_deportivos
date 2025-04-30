package com.dam_proyect.api_figth.service;

import com.dam_proyect.api_figth.dto.RegisterRequest;
import com.dam_proyect.api_figth.model.User;
import com.dam_proyect.api_figth.model.tokens.VerificationToken;
import com.dam_proyect.api_figth.repository.UserRepository;
import com.dam_proyect.api_figth.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final VerificationTokenRepository tokenRepository;

    public AuthService(UserRepository userRepository, VerificationTokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    public void registerUser(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()) != null) {
            throw new RuntimeException("User already exists");
        }

        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername(request.getUsername());
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhone(request.getPhone());
        user.setEnabled(false);

        userRepository.save(user);

        // Generar token único
        String token = UUID.randomUUID().toString();
        LocalDateTime expiry = LocalDateTime.now().plusMinutes(15); // Caduca en 15 min

        VerificationToken verificationToken = new VerificationToken(token, user, expiry);
        tokenRepository.save(verificationToken);

        // Simulación del envío de email
        System.out.println("Haz clic para activar tu cuenta:");
        System.out.println("http://localhost:8080/api/auth/confirm?token=" + token);
    }

    public String confirmToken(String token) {
        VerificationToken verificationToken = tokenRepository.findById(token)
                .orElseThrow(() -> new RuntimeException("Token no válido"));

        if (verificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expirado");
        }

        User user = verificationToken.getUser();
        user.setEnabled(true);
        userRepository.save(user);
        tokenRepository.delete(verificationToken); // El token ya no se necesita

        return "Cuenta activada correctamente";
    }

    public boolean login(String username, String password) {
        User user = userRepository.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }
}
