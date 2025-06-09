package com.dam_proyect.api_figth.repository;

import com.dam_proyect.api_figth.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClubRepository extends JpaRepository<Club, String> {
    Optional<Club> findByName(String name);
    Optional<Club> findById(String id);
}
