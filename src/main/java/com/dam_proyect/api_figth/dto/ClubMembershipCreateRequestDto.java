package com.dam_proyect.api_figth.dto;

import com.dam_proyect.api_figth.Commons.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClubMembershipCreateRequestDto {
    @NotBlank(message = "El ID del usuario es obligatorio")
    private String userId;
    @NotBlank(message = "El ID del club es obligatorio")
    private String clubId;
    @NotNull(message = "El rol es obligatorio")
    private Role role;

}
