package com.dam_proyect.api_figth.dto;

import lombok.*;
public class ClubCreateRequestDto {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ClubCreateRequest {
        private String name;
        private String description;
        private String imageUrl;
        private String location;
        private String contactEmail;
    }
}
