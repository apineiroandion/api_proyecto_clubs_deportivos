package com.dam_proyect.api_figth.service;

import com.dam_proyect.api_figth.dto.*;
import com.dam_proyect.api_figth.mapper.ClubMembershipMapper;
import com.dam_proyect.api_figth.model.Club;
import com.dam_proyect.api_figth.model.ClubMembership;
import com.dam_proyect.api_figth.model.User;
import com.dam_proyect.api_figth.repository.ClubMembershipRepository;
import com.dam_proyect.api_figth.service.contract.ClubMembershipService;
import com.dam_proyect.api_figth.service.contract.ClubService;
import com.dam_proyect.api_figth.service.contract.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClubMembershipServiceImpl implements ClubMembershipService {

    private final ClubMembershipRepository repository;
    private final ClubMembershipMapper mapper;

    public ClubMembershipServiceImpl(
            ClubMembershipRepository clubMembershipRepository,
            ClubMembershipMapper clubMembershipMapper) {
        this.repository = clubMembershipRepository;
        this.mapper = clubMembershipMapper;
    }

    @Override
    public ResponseBaseDto<ClubMembershipResponseDto> joinClub(ClubMembershipCreateRequestDto dto) {
        ClubMembership membership = mapper.toEntity(dto);
        membership.setId(UUID.randomUUID().toString());

        ClubMembership savedMembership = repository.save(membership);
        return new ResponseBaseDto<>(
                "Joined club successfully",
                true,
                mapper.toResponseDto(savedMembership)
        );
    }

    @Override
    public ResponseBaseDto<Void> leaveClub(String membershipId) {
        if (!repository.existsById(membershipId)) {
            return new ResponseBaseDto<>("Membership not found", false, null);
        }
        repository.deleteById(membershipId);
        return new ResponseBaseDto<>("Left club successfully", true, null);
    }

    @Override
    public ResponseBaseDto<Void> leaveClub(String userId, String clubId) {
        Optional<ClubMembership> membership = repository.findByUserIdAndClubId(userId, clubId);
        if (membership.isEmpty()) {
            return new ResponseBaseDto<>("Membership not found", false, null);
        }
        repository.delete(membership.get());
        return new ResponseBaseDto<>("Left club successfully", true, null);
    }

    @Override
    public ResponseBaseDto<List<ClubMembershipResponseDto>> getMembershipsByUser(String userId) {
        List<ClubMembership> memberships = repository.findByUserId(userId);
        List<ClubMembershipResponseDto> responseDtos = mapper.toResponseDtoList(memberships);
        return new ResponseBaseDto<>(
                "Fetched memberships successfully",
                true,
                responseDtos
        );
    }

    @Override
    public ResponseBaseDto<List<ClubMembershipResponseDto>> getMembershipsByClub(String clubId) {
        List<ClubMembership> memberships = repository.findByClubId(clubId);
        List<ClubMembershipResponseDto> responseDtos = mapper.toResponseDtoList(memberships);
        return new ResponseBaseDto<>(
                "Fetched memberships successfully",
                true,
                responseDtos
        );
    }
}
