package com.dam_proyect.api_figth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequestDto {
    private Optional<String> name = Optional.empty();
    private Optional<String> surname = Optional.empty();
    private Optional<String> email = Optional.empty();
    private Optional<String> phone = Optional.empty();
    private Optional<String> profilePicture = Optional.empty();
}
