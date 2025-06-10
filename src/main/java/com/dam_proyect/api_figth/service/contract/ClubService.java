package com.dam_proyect.api_figth.service.contract;

import com.dam_proyect.api_figth.dto.ClubCreateRequestDto;
import com.dam_proyect.api_figth.dto.ClubResponseDto;
import com.dam_proyect.api_figth.dto.ClubUpdateRequestDto;
import com.dam_proyect.api_figth.dto.ResponseBaseDto;

import java.util.List;

public interface ClubService {
    ResponseBaseDto<List<ClubResponseDto>> getAllClubs();
    ResponseBaseDto<ClubResponseDto> getClubById(String id);
    ResponseBaseDto<ClubResponseDto> getClubByName(String name);
    ResponseBaseDto<ClubResponseDto> createClub(ClubCreateRequestDto clubDto);
    ResponseBaseDto<ClubResponseDto> updateClub(String id, ClubUpdateRequestDto clubDto);
    ResponseBaseDto<Void> deleteClub(String id);
    ResponseBaseDto<List<ClubResponseDto>> getClubsByMembership(String membershipId);
    ResponseBaseDto<List<ClubResponseDto>> getClubsByUser(String userId);
    ResponseBaseDto<List<ClubResponseDto>> getClubsByLocation(String location);
}
