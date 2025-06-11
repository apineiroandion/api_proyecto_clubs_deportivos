package com.dam_proyect.api_figth.service.contract;

import com.dam_proyect.api_figth.dto.RegisterRequestDto;
import com.dam_proyect.api_figth.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
    Boolean registerUser(RegisterRequestDto dto);

    String activateAccount(String token);

    User getUserByUsername(String username);

    User getUserById(String id);

    void sendVerificationEmail(String email);

    void resetPassword(String email, String newPassword);

    void changePassword(String oldPassword, String newPassword);

    String uploadUserImage(MultipartFile image) throws IOException;
}
