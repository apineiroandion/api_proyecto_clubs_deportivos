package com.dam_proyect.api_figth.service;

import com.dam_proyect.api_figth.dto.*;
import com.dam_proyect.api_figth.mapper.UserMapper;
import com.dam_proyect.api_figth.model.User;
import com.dam_proyect.api_figth.model.tokens.ResetPasswordToken;
import com.dam_proyect.api_figth.model.tokens.VerificationToken;
import com.dam_proyect.api_figth.repository.ResetPasswordTokenRepository;
import com.dam_proyect.api_figth.repository.UserRepository;
import com.dam_proyect.api_figth.repository.VerificationTokenRepository;
import com.dam_proyect.api_figth.service.contract.EmailService;
import com.dam_proyect.api_figth.service.contract.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final VerificationTokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;
    private final ResetPasswordTokenRepository resetPasswordTokenRepository;
    private final EmailService emailService;

    public UserServiceImpl(UserRepository userRepository, VerificationTokenRepository tokenRepository,
                           PasswordEncoder passwordEncoder, UserMapper mapper, EmailService emailService,
                           ResetPasswordTokenRepository resetPasswordTokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
        this.emailService = emailService;
        this.resetPasswordTokenRepository = resetPasswordTokenRepository;
    }

    public ResponseBaseDto<UserDto> getUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        UserDto userDto = mapper.toDto(user);
        return new ResponseBaseDto<>("Usuario encontrado", true, userDto);
    }

    public ResponseBaseDto<UserDto> getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return new ResponseBaseDto<>("Usuario no encontrado", false, null);
        }
        UserDto userDto = mapper.toDto(user);
        return new ResponseBaseDto<>("Usuario encontrado", true, userDto);
    }

    public ResponseBaseDto<UserDto> registerUser(RegisterRequestDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            return new ResponseBaseDto<>("El usuario ya existe", false, null);
        }
        if (userRepository.existsByUsername(dto.getUsername())) {
            return new ResponseBaseDto<>("El nombre de usuario ya está en uso", false, null);
        }

        User user = mapper.toEntity(dto);
        user.setId(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEnabled(false);
        userRepository.save(user);

        VerificationToken token = new VerificationToken(UUID.randomUUID().toString(), user, LocalDateTime.now().plusDays(1));
        tokenRepository.save(token);

        if (!sendVerificationEmail(user.getEmail(), token.getToken())) {
            return new ResponseBaseDto<>("Error al enviar el correo electrónico de verificación", false, null);
        }

        UserDto userDto = mapper.toDto(user);
        return new ResponseBaseDto<>("Registro exitoso", true, userDto);
    }

    public ResponseBaseDto<String> activateAccount(String token) {
        VerificationToken verificationToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Token de verificación no encontrado"));

        if (verificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            return new ResponseBaseDto<>("El token de verificación ha expirado", false, null);
        }

        User user = verificationToken.getUser();
        user.setEnabled(true);
        userRepository.save(user);

        tokenRepository.delete(verificationToken);

        return new ResponseBaseDto<>("Cuenta activada correctamente", true, null);
    }

    public ResponseBaseDto<Void> resetPassword(String email, String newPassword) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("Usuario no encontrado");
        }
        if (user.isEmpty()) {
            return new ResponseBaseDto<>("El correo electrónico no está registrado", false, null);
        }
        User existingUser = user.get();
        String token = UUID.randomUUID().toString();
        ResetPasswordToken resetToken = new ResetPasswordToken(token, existingUser, LocalDateTime.now().plusHours(1));
        resetPasswordTokenRepository.save(resetToken);
        if (!sendResetPasswordEmail(existingUser.getEmail(), resetToken.getToken())) {
            return new ResponseBaseDto<>("Error al enviar el correo electrónico de restablecimiento de contraseña", false, null);
        }
        return new ResponseBaseDto<>("Se ha enviado un correo electrónico para restablecer la contraseña", true, null);
    }

    public ResponseBaseDto<Void> changePassword(String id, UserChagePasswordRequestDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(dto.getOldPassword(), user.getPassword())) {
            return new ResponseBaseDto<>("La contraseña actual es incorrecta", false, null);
        }

        if (!dto.getNewPassword().equals(dto.getConfirmNewPassword())) {
            return new ResponseBaseDto<>("Las nuevas contraseñas no coinciden", false, null);
        }

        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        userRepository.save(user);

        return new ResponseBaseDto<>("Contraseña cambiada correctamente", true, null);
    }

    public ResponseBaseDto<Void> newPassword(String token, UserNewPasswordRequestDto dto) {
        ResetPasswordToken resetToken = resetPasswordTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Token de restablecimiento de contraseña no encontrado"));

        if (resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            return new ResponseBaseDto<>("El token de restablecimiento de contraseña ha expirado", false, null);
        }

        User user = resetToken.getUser();
        if (!dto.getNewPassword().equals(dto.getConfirmNewPassword())) {
            return new ResponseBaseDto<>("Las nuevas contraseñas no coinciden", false, null);
        }

        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        userRepository.save(user);

        resetPasswordTokenRepository.delete(resetToken);

        return new ResponseBaseDto<>("Contraseña restablecida correctamente", true, null);
    }

    public ResponseBaseDto<String> uploadUserImage(MultipartFile image) throws IOException {
        // TODO: Implementar lógica para subir la imagen del usuario
        return new ResponseBaseDto<>("Imagen de usuario subida correctamente", true, null);
    }

    public ResponseBaseDto<UserDto> updateUser(String id, UserUpdateRequestDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        dto.getName().ifPresent(user::setName);
        dto.getSurname().ifPresent(user::setSurname);
        dto.getPhone().ifPresent(user::setPhone);
        dto.getProfilePicture().ifPresent(user::setProfilePicture);

        dto.getEmail().ifPresent(email -> {
            if (!email.equals(user.getEmail()) && userRepository.existsByEmail(email)) {
                throw new RuntimeException("El correo electrónico ya está en uso");
            }
            user.setEmail(email);
        });

        userRepository.save(user);

        UserDto updatedUserDto = mapper.toDto(user);
        return new ResponseBaseDto<>("Usuario actualizado correctamente", true, updatedUserDto);
    }

    public ResponseBaseDto<Void> deleteUser(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        VerificationToken token = tokenRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Token de verificación no encontrado"));

        if (token != null) {
            tokenRepository.delete(token);
        }
        userRepository.delete(user);
        return new ResponseBaseDto<>("Usuario eliminado correctamente", true, null);
    }

    // TODO: Arreglar el envio de correos electronicos
    private Boolean sendVerificationEmail(String email, String token) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            return false;
        }

        String verificationUrl = "http://localhost:8080/user/activate?token=" + token;
        String subject = "Verificación de cuenta";
        String body = "Por favor, haz clic en el siguiente enlace para verificar tu cuenta: " + verificationUrl;

//        emailService.sendEmail(email, subject, body);

        System.out.println("Enviando correo electrónico de verificación a: " + email);
        System.out.println("Asunto: " + subject);
        System.out.println("Cuerpo: " + body);

        return true;

    }

    // TODO: Arreglar el envio de correos electronicos
    private Boolean sendResetPasswordEmail(String email, String token) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            return false;
        }

        String resetUrl = "http://localhost:8080/user/new-password?token=" + token;
        String subject = "Restablecimiento de contraseña";
        String body = "Por favor, haz clic en el siguiente enlace para restablecer tu contraseña: " + resetUrl;

//        emailService.sendEmail(email, subject, body);

        System.out.println("Enviando correo electrónico de restablecimiento de contraseña a: " + email);
        System.out.println("Asunto: " + subject);
        System.out.println("Cuerpo: " + body);

        return true;
    }

}
