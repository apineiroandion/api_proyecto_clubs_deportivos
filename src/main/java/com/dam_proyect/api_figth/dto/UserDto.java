package com.dam_proyect.api_figth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String id;
    private String username;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private Boolean enabled;
    private LocalDate createdAt;
    private String profilePicture;
}
