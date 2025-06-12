package com.dam_proyect.api_figth.service.contract;

import com.dam_proyect.api_figth.dto.ClubMembershipCreateRequestDto;
import com.dam_proyect.api_figth.dto.ClubMembershipResponseDto;
import com.dam_proyect.api_figth.dto.ResponseBaseDto;

import java.util.List;

public interface ClubMembershipService {
    ResponseBaseDto<ClubMembershipResponseDto> joinClub(ClubMembershipCreateRequestDto dto);
    ResponseBaseDto<Void> leaveClub(String membershipId);
    ResponseBaseDto<Void> leaveClub(String userId, String clubId);
    ResponseBaseDto<ClubMembershipResponseDto> getClubMembership(String membershipId);
    ResponseBaseDto<List<ClubMembershipResponseDto>> getMembershipsByUser(String userId);
    ResponseBaseDto<List<ClubMembershipResponseDto>> getMembershipsByClub(String clubId);
}
