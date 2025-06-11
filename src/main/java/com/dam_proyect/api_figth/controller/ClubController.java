package com.dam_proyect.api_figth.controller;

import com.dam_proyect.api_figth.dto.*;
import com.dam_proyect.api_figth.model.ClubMembership;
import com.dam_proyect.api_figth.service.contract.ClubMembershipService;
import com.dam_proyect.api_figth.service.contract.ClubService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clubs")
public class ClubController {

    private final ClubService clubService;
    private final ClubMembershipService clubMembershipService;

    public ClubController(ClubService clubService,
                          ClubMembershipService clubMembershipService) {
        this.clubService = clubService;
        this.clubMembershipService = clubMembershipService;
    }

    @PostMapping()
    public ResponseBaseDto<ClubResponseDto> createClub(
        @Valid @RequestBody ClubCreateRequestDto clubCreateRequestDto) {
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

    @PostMapping("/join")
    public ResponseBaseDto<ClubMembershipResponseDto> joinClub(@Valid @RequestBody ClubMembershipCreateRequestDto dto) {
        try {
            return clubMembershipService.joinClub(dto);
        } catch (Exception e) {
            return new ResponseBaseDto<>(
                    "Error joining club: " + e.getMessage(),
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
