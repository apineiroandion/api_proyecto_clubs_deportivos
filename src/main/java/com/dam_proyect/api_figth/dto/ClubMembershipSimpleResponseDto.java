package com.dam_proyect.api_figth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClubMembershipSimpleResponseDto {
    private String id;
    private UserDto user;
    private String role;
}
