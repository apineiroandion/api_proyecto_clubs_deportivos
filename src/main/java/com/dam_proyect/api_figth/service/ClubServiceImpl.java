package com.dam_proyect.api_figth.service;

import com.dam_proyect.api_figth.dto.*;
import com.dam_proyect.api_figth.mapper.ClubMapper;
import com.dam_proyect.api_figth.model.Club;
import com.dam_proyect.api_figth.repository.ClubRepository;
import com.dam_proyect.api_figth.service.contract.ClubService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClubServiceImpl implements ClubService {

    private final ClubRepository repository;
    private final ClubMapper mapper;

    public ClubServiceImpl(
            ClubRepository repository,
            ClubMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }



    @Override
    public ResponseBaseDto<List<ClubResponseDto>> getAllClubs() {
        List<Club> clubs = repository.findAll();
        List<ClubResponseDto> clubResponseDTOs = mapper.toResponseDtos(clubs);

        if (clubResponseDTOs.isEmpty()) {
            return new ResponseBaseDto<>("No clubs found", false, null);
        }

        return new ResponseBaseDto<>("Clubs retrieved successfully", true, clubResponseDTOs);
    }

    @Override
    public ResponseBaseDto<ClubResponseDto> getClubById(String id) {
        Club club = repository.findById(id).orElse(null);
        if (club == null) {
            return new ResponseBaseDto<>("Club not found", false, null);
        }
        ClubResponseDto responseDto = mapper.toResponseDto(club);
        return new ResponseBaseDto<>("Club retrieved successfully", true, responseDto);
    }

    @Override
    public ResponseBaseDto<ClubResponseDto> getClubByName(String name) {
        Optional<Club> clubOptional = repository.findByName(name);
        if (clubOptional.isEmpty()) {
            return new ResponseBaseDto<>("Club not found", false, null);
        }
        Club club = clubOptional.get();
        ClubResponseDto responseDto = mapper.toResponseDto(club);
        return new ResponseBaseDto<>("Club retrieved successfully", true, responseDto);
    }

    @Override
    public ResponseBaseDto<ClubResponseDto> createClub(ClubCreateRequestDto clubDto) {
        Club club = mapper.toEntity(clubDto);
        club.setId(UUID.randomUUID().toString());
        club.setCreatedAt(LocalDateTime.now().toString());

        Club savedClub = repository.save(club);
        ClubResponseDto responseDto = mapper.toResponseDto(savedClub);

        return new ResponseBaseDto<>("Club created successfully", true, responseDto);
    }

    @Override
    public ResponseBaseDto<ClubResponseDto> updateClub(String id, ClubUpdateRequestDto clubDto) {
        Club club = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Club not found"));
        mapper.updateClubFromDto(clubDto, club);
        repository.save(club);
        ClubResponseDto responseDto = mapper.toResponseDto(club);
        return new ResponseBaseDto<>("Club updated successfully", true, responseDto);
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
    public ResponseBaseDto<List<ClubResponseDto>> getClubsByLocation(String location) {
        List<Club> clubs = repository.findByLocation(location);
        List<ClubResponseDto> clubResponseDTOs = mapper.toResponseDtos(clubs);

        if (clubResponseDTOs.isEmpty()) {
            return new ResponseBaseDto<>("No clubs found for the specified location", false, null);
        }

        return new ResponseBaseDto<>("Clubs retrieved successfully", true, clubResponseDTOs);
    }
}
