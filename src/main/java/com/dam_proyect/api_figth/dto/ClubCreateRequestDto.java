package com.dam_proyect.api_figth.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClubCreateRequestDto {

    @NotBlank(message = "El nombre del Club es obligatorio")
    private String name;

    @NotBlank(message = "La descripción del Club es obligatoria")
    private String description;

    @NotBlank(message = "La ubicación del Club es obligatoria")
    private String location;

    @NotBlank(message = "El email de contacto es obligatorio")
    @Email(message = "El formato del email no es válido")
    private String contactEmail;

    @NotBlank(message = "El teléfono es obligatorio")
    @Size(min = 10, max = 15, message = "El teléfono debe tener entre 10 y 15 caracteres")
    private String contactPhone;

    @Nullable
    private String imageUrl;
}
