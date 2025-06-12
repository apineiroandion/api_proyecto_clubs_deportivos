package com.dam_proyect.api_figth.service.contract;

import com.dam_proyect.api_figth.dto.*;
import com.dam_proyect.api_figth.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
    ResponseBaseDto<UserDto> registerUser(RegisterRequestDto dto);

    ResponseBaseDto<String> activateAccount(String token);

    ResponseBaseDto<UserDto> getUserByUsername(String username);

    ResponseBaseDto<UserDto> getUserById(String id);

    ResponseBaseDto<Void> resetPassword(String email, String newPassword);

    ResponseBaseDto<Void> changePassword(String id, UserChagePasswordRequestDto dto);

    ResponseBaseDto<Void> newPassword(String token, UserNewPasswordRequestDto dto);

    ResponseBaseDto<String> uploadUserImage(MultipartFile image) throws IOException;

    ResponseBaseDto<UserDto> updateUser(String id, UserUpdateRequestDto userDto);

    ResponseBaseDto<Void> deleteUser(String id);
}
