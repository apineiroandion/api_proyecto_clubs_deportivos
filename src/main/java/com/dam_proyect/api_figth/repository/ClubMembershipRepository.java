package com.dam_proyect.api_figth.repository;

import com.dam_proyect.api_figth.model.ClubMembership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClubMembershipRepository extends JpaRepository<ClubMembership, String> {
    List<ClubMembership> findByUserId(String userId);
    List<ClubMembership> findByClubId(String clubId);
    Optional<ClubMembership> findByUserIdAndClubId(String userId, String clubId);
}
