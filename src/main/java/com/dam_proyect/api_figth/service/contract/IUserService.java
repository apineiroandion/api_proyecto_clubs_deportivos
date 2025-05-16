package com.dam_proyect.api_figth.service.contract;

import com.dam_proyect.api_figth.dto.RegisterRequestDto;
import com.dam_proyect.api_figth.model.User;

public interface IUserService {
    Boolean registerUser(RegisterRequestDto dto);

    String activateAccount(String token);

    User getUserByUsername(String username);

    void sendVerificationEmail(String email);

    void resetPassword(String email, String newPassword);

    void changePassword(String oldPassword, String newPassword);
}
