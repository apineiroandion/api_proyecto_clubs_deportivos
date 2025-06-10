package com.dam_proyect.api_figth.controller;

import com.dam_proyect.api_figth.dto.ClubCreateRequestDto;
import com.dam_proyect.api_figth.dto.ClubResponseDto;
import com.dam_proyect.api_figth.dto.ResponseBaseDto;
import com.dam_proyect.api_figth.service.contract.ClubService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clubs")
public class ClubController {

    private final ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @PostMapping()
    public ResponseBaseDto<ClubResponseDto> createClub(ClubCreateRequestDto clubCreateRequestDto) {
        try {
            return clubService.createClub(clubCreateRequestDto);
        } catch (Exception e) {
            return new ResponseBaseDto<>(
                    "Error creating club: " + e.getMessage(),
                    false,
                    null
            );
        }
    }

    @DeleteMapping("{id}")
    public ResponseBaseDto<Void> deleteClub(@PathVariable String id) {
        try {
            return clubService.deleteClub(id);
        } catch (Exception e) {
            return new ResponseBaseDto<>(
                    "Error deleting club: " + e.getMessage(),
                    false,
                    null
            );
        }
    }
}
