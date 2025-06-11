package com.dam_proyect.api_figth.service.contract;

import com.dam_proyect.api_figth.dto.*;
import com.dam_proyect.api_figth.model.Club;

import java.util.List;

public interface ClubService {
    ResponseBaseDto<List<ClubResponseDto>> getAllClubs();
    ResponseBaseDto<ClubResponseDto> getClubById(String id);
    Club getClubByIdNoResponse(String id);
    ResponseBaseDto<ClubResponseDto> getClubByName(String name);
    ResponseBaseDto<ClubResponseDto> createClub(ClubCreateRequestDto clubDto);
    ResponseBaseDto<ClubResponseDto> updateClub(String id, ClubUpdateRequestDto clubDto);
    ResponseBaseDto<Void> deleteClub(String id);
    ResponseBaseDto<List<ClubResponseDto>> getClubsByMembership(String membershipId);
    ResponseBaseDto<List<ClubResponseDto>> getClubsByUser(String userId);
    ResponseBaseDto<List<ClubResponseDto>> getClubsByLocation(String location);
}
