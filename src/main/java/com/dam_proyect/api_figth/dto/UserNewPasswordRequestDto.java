package com.dam_proyect.api_figth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserNewPasswordRequestDto {
    private String newPassword;
    private String confirmNewPassword;
}
