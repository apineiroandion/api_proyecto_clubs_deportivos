package com.dam_proyect.api_figth.service;

import com.dam_proyect.api_figth.dto.RegisterRequestDto;
import com.dam_proyect.api_figth.model.User;
import com.dam_proyect.api_figth.model.tokens.VerificationToken;
import com.dam_proyect.api_figth.repository.UserRepository;
import com.dam_proyect.api_figth.repository.VerificationTokenRepository;
import com.dam_proyect.api_figth.service.contract.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VerificationTokenRepository tokenRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Boolean registerUser(RegisterRequestDto request) {
        if (userRepository.findByUsername(request.getUsername()) != null) {
            throw new RuntimeException("User already exists");
        }

        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername(request.getUsername());
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
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
        return true;
    }

    public String activateAccount(String token) {
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

    public void sendVerificationEmail(String email) {
        // TODO: Implementar lógica para enviar un correo electrónico de verificación
        // Esto podría incluir el uso de un servicio de correo electrónico como SendGrid, Amazon SES, etc.
        System.out.println("Enviando correo de verificación a " + email);
    }

    public void resetPassword(String email, String newPassword) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("Usuario no encontrado");
        }
        // TODO: implementar lógica para enviar un correo electrónico de restablecimiento de contraseña
    }

    public void changePassword(String oldPassword, String newPassword) {
        // TODO: Implementar lógica para cambiar la contraseña del usuario autenticado
    }

    public String uploadUserImage(MultipartFile image) throws IOException {
        // TODO: Implementar lógica para subir la imagen del usuario
        return "Imagen subida correctamente";
    }

    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }


}
