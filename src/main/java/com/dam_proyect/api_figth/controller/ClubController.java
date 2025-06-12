package com.dam_proyect.api_figth.controller;

import com.dam_proyect.api_figth.dto.*;
import com.dam_proyect.api_figth.model.ClubMembership;
import com.dam_proyect.api_figth.service.contract.ClubMembershipService;
import com.dam_proyect.api_figth.service.contract.ClubService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseBaseDto<List<ClubResponseDto>> getAllClubs() {
        try {
            return clubService.getAllClubs();
        } catch (Exception e) {
            return new ResponseBaseDto<>(
                    "Error retrieving clubs: " + e.getMessage(),
                    false,
                    null
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseBaseDto<ClubResponseDto> getClubById(@PathVariable String id) {
        try {
            return clubService.getClubById(id);
        } catch (Exception e) {
            return new ResponseBaseDto<>(
                    "Error retrieving club by ID: " + e.getMessage(),
                    false,
                    null
            );
        }
    }

    @GetMapping("/name/{name}")
    public ResponseBaseDto<ClubResponseDto> getClubByName(@PathVariable String name) {
        try {
            return clubService.getClubByName(name);
        } catch (Exception e) {
            return new ResponseBaseDto<>(
                    "Error retrieving club by name: " + e.getMessage(),
                    false,
                    null
            );
        }
    }

    @GetMapping("/location/{location}")
    public ResponseBaseDto<List<ClubResponseDto>> getClubsByLocation(@PathVariable String location) {
        try {
            return clubService.getClubsByLocation(location);
        } catch (Exception e) {
            return new ResponseBaseDto<>(
                    "Error retrieving clubs by location: " + e.getMessage(),
                    false,
                    null
            );
        }
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

    @PutMapping("/{id}")
    public ResponseBaseDto<ClubResponseDto> updateClub(
            @PathVariable String id,
            @Valid @RequestBody ClubUpdateRequestDto clubUpdateRequestDto) {
        try {
            return clubService.updateClub(id, clubUpdateRequestDto);
        } catch (Exception e) {
            return new ResponseBaseDto<>(
                    "Error updating club: " + e.getMessage(),
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

    @GetMapping("/memberships/{membershipId}")
    public ResponseBaseDto<ClubMembershipResponseDto> getClubMembership(@PathVariable String membershipId) {
        try {
            return clubMembershipService.getClubMembership(membershipId);
        } catch (Exception e) {
            return new ResponseBaseDto<>(
                    "Error retrieving club membership: " + e.getMessage(),
                    false,
                    null
            );
        }
    }

    @DeleteMapping("/leave/{membershipId}")
    public ResponseBaseDto<Void> leaveClub(@PathVariable String membershipId) {
        try {
            return clubMembershipService.leaveClub(membershipId);
        } catch (Exception e) {
            return new ResponseBaseDto<>(
                    "Error leaving club: " + e.getMessage(),
                    false,
                    null
            );
        }
    }

    @GetMapping("memberships/club/{clubId}")
    public ResponseBaseDto<List<ClubMembershipResponseDto>> getMembershipsByClub(@PathVariable String clubId) {
        try {
            return clubMembershipService.getMembershipsByClub(clubId);
        } catch (Exception e) {
            return new ResponseBaseDto<>(
                    "Error retrieving memberships by club: " + e.getMessage(),
                    false,
                    null
            );
        }
    }

    @DeleteMapping("/leave/{userId}/{clubId}")
    public ResponseBaseDto<Void> leaveClub(@PathVariable String userId, @PathVariable String clubId) {
        try {
            return clubMembershipService.leaveClub(userId, clubId);
        } catch (Exception e) {
            return new ResponseBaseDto<>(
                    "Error leaving club: " + e.getMessage(),
                    false,
                    null
            );
        }
    }

    @GetMapping("memberships/user/{userId}")
    public ResponseBaseDto<List<ClubMembershipResponseDto>> getMembershipsByUser(@PathVariable String userId) {
        try {
            return clubMembershipService.getMembershipsByUser(userId);
        } catch (Exception e) {
            return new ResponseBaseDto<>(
                    "Error retrieving memberships by user: " + e.getMessage(),
                    false,
                    null
            );
        }
    }
}
