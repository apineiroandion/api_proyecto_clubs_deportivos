package com.dam_proyect.api_figth.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClubResponseDto {
    private String id;
    private String name;
    private String description;
    private String location;
    private String imageUrl;
    private String contactEmail;
    private String contactPhone;
    private String createdAt;

}
