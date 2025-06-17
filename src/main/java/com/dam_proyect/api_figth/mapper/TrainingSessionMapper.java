package com.dam_proyect.api_figth.mapper;

import com.dam_proyect.api_figth.dto.TrainingSessionCreateRequestDto;
import com.dam_proyect.api_figth.dto.TrainingSessionResponseDto;
import com.dam_proyect.api_figth.dto.TrainingSessionUpdateRequestDto;
import com.dam_proyect.api_figth.model.Club;
import com.dam_proyect.api_figth.model.ClubMembership;
import com.dam_proyect.api_figth.model.TrainingSession;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.LocalDate;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TrainingSessionMapper {

    @Mapping(source = "clubId", target = "club")
    @Mapping(source = "trainerId", target = "trainer")
    @Mapping(source = "date", target = "date")
    TrainingSession toEntity(TrainingSessionCreateRequestDto dto);

    @Mapping(source = "club", target = "club")
    @Mapping(source = "trainer", target = "trainer")
    @Mapping(source = "date", target = "date")
    @Mapping(source = "attendees", target = "attendees")
    TrainingSessionResponseDto toDto(TrainingSession entity);

    List<TrainingSessionResponseDto> toDtoList(List<TrainingSession> entities);

    void updateTrainingSessionFromDto(TrainingSessionUpdateRequestDto dto, @MappingTarget TrainingSession trainingSession);

    // Helpers: ID (String) → entidad
    default Club mapClub(String id) {
        if (id == null) return null;
        Club club = new Club();
        club.setId(id);
        return club;
    }

    default ClubMembership mapTrainer(String id) {
        if (id == null) return null;
        ClubMembership trainer = new ClubMembership();
        trainer.setId(id);
        return trainer;
    }

    // Helpers: entidad → ID (String)
    default String clubToClubId(Club club) {
        return club != null ? club.getId() : null;
    }

    default String trainerToTrainerId(ClubMembership trainer) {
        return trainer != null ? trainer.getId() : null;
    }

    // Fecha: String <-> LocalDate
    default LocalDate mapDate(String date) {
        if (date == null) return null;
        return LocalDate.parse(date);
    }

    default String mapDate(LocalDate date) {
        return date != null ? date.toString() : null;
    }
}
