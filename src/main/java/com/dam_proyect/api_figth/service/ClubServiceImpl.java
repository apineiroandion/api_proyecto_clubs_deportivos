package com.dam_proyect.api_figth.service;

import com.dam_proyect.api_figth.dto.ClubCreateRequestDto;
import com.dam_proyect.api_figth.dto.ClubResponseDto;
import com.dam_proyect.api_figth.dto.ClubUpdateRequestDto;
import com.dam_proyect.api_figth.dto.ResponseBaseDto;
import com.dam_proyect.api_figth.mapper.ClubMapper;
import com.dam_proyect.api_figth.model.Club;
import com.dam_proyect.api_figth.repository.ClubRepository;
import com.dam_proyect.api_figth.service.contract.ClubService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClubServiceImpl implements ClubService {

    private final ClubRepository repository;
    private final ClubMapper mapper;

    public ClubServiceImpl(ClubRepository repository, ClubMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public ResponseBaseDto<List<ClubResponseDto>> getAllClubs() {
        // Implementation logic
        return null;
    }

    @Override
    public ResponseBaseDto<ClubResponseDto> getClubById(String id) {
        // Implementation logic
        return null;
    }

    @Override
    public ResponseBaseDto<ClubResponseDto> getClubByName(String name) {
        // Implementation logic
        return null;
    }

    @Override
    public ResponseBaseDto<ClubResponseDto> createClub(ClubCreateRequestDto clubDto) {
        Club club = mapper.toEntity(clubDto);
        club.setId(UUID.randomUUID().toString());
        club.setCreatedAt(java.time.LocalDateTime.now().toString()); // Set current time as createdAt
        Club savedClub = repository.save(club);
        ClubResponseDto responseDto = mapper.toResponseDto(savedClub);
        return new ResponseBaseDto<>(
                "Club created successfully",
                true,
                responseDto
        );
    }

    @Override
    public ResponseBaseDto<ClubResponseDto> updateClub(String id, ClubUpdateRequestDto clubDto) {
        // Implementation logic
        return null;
    }

    @Override
    public ResponseBaseDto<Void> deleteClub(String id) {
        if (!repository.existsById(id)) {
            return new ResponseBaseDto<>("Club not found", false, null);
        }
        repository.deleteById(id);
        return new ResponseBaseDto<>("Club deleted successfully", true, null);
    }

    @Override
    public ResponseBaseDto<List<ClubResponseDto>> getClubsByMembership(String membershipId) {
        // Implementation logic
        return null;
    }

    @Override
    public ResponseBaseDto<List<ClubResponseDto>> getClubsByUser(String userId) {
        // Implementation logic
        return null;
    }

    @Override
    public ResponseBaseDto<List<ClubResponseDto>> getClubsByLocation(String location) {
        // Implementation logic
        return null;
    }
}
