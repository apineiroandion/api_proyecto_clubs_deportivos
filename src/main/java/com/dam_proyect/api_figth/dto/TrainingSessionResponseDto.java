package com.dam_proyect.api_figth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainingSessionResponseDto {
    private String id;
    private String title;
    private String description;
    private String date;
    private ClubResponseDto club;
    private ClubMembershipSimpleResponseDto trainer;
    private ClubMembershipSimpleResponseDto[] attendees;
}
