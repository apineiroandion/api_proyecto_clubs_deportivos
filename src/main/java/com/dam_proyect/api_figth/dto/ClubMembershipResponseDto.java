package com.dam_proyect.api_figth.dto;

import com.dam_proyect.api_figth.Commons.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClubMembershipResponseDto {
    private String id;
    private UserDto user;
    private ClubResponseDto club;
    private Role role;
}
