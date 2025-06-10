package com.dam_proyect.api_figth.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClubUpdateRequestDto {
    private Optional<String> name = Optional.empty();
    private Optional<String> description = Optional.empty();
    private Optional<String> location = Optional.empty();
    private Optional<String> imageUrl = Optional.empty();
    private Optional<String> contactEmail = Optional.empty();
    private Optional<String> contactPhone = Optional.empty();
}
