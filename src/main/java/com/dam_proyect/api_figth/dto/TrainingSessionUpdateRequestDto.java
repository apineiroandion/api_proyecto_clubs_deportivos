package com.dam_proyect.api_figth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainingSessionUpdateRequestDto {
    private String id;
    private String title;
    private String description;
    private String date; // ISO 8601 format (e.g., "2023-10-01")
    private String clubId;
    private String trainerId;
}
