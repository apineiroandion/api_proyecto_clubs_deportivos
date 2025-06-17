package com.dam_proyect.api_figth.repository;

import com.dam_proyect.api_figth.model.TrainingSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TrainingSessionRepository extends JpaRepository<TrainingSession, String> {
    Optional<TrainingSession> findById(String id);
    List<TrainingSession> findByClubId(String clubId);
    List<TrainingSession> findByTrainerId(String trainerId);
    List<TrainingSession> findByAttendeesId(String attendeeId);

}
