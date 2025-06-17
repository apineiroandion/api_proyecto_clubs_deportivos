package com.dam_proyect.api_figth.service;

import com.dam_proyect.api_figth.dto.*;
import com.dam_proyect.api_figth.mapper.TrainingSessionMapper;
import com.dam_proyect.api_figth.model.ClubMembership;
import com.dam_proyect.api_figth.model.TrainingSession;
import com.dam_proyect.api_figth.repository.ClubMembershipRepository;
import com.dam_proyect.api_figth.repository.TrainingSessionRepository;
import com.dam_proyect.api_figth.service.contract.ClubMembershipService;
import com.dam_proyect.api_figth.service.contract.TrainingSessionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TrainingSessionImpl implements TrainingSessionService {

    private final TrainingSessionRepository repository;
    private final ClubMembershipRepository clubMembershipRepository;
    private final TrainingSessionMapper mapper;

    public TrainingSessionImpl(
            TrainingSessionRepository repository,
            ClubMembershipRepository clubMembershipRepository,
            TrainingSessionMapper mapper) {
        this.repository = repository;
        this.clubMembershipRepository = clubMembershipRepository;
        this.mapper = mapper;
    }

    @Override
    public ResponseBaseDto<List<TrainingSessionResponseDto>> getAllTrainingSessions(){
        List<TrainingSessionResponseDto> trainingSessions = mapper.toDtoList(repository.findAll());
        if (trainingSessions.isEmpty()) {
            return new ResponseBaseDto<>("No training sessions found", false, null);
        }
        return new ResponseBaseDto<>("Training sessions retrieved successfully", true, trainingSessions);
    }
    public ResponseBaseDto<TrainingSessionResponseDto> getTrainingSessionById(String id){
        TrainingSessionResponseDto trainingSession = mapper.toDto(repository.findById(id).orElse(null));
        if (trainingSession == null) {
            return new ResponseBaseDto<>("Training session not found", false, null);
        }
        return new ResponseBaseDto<>("Training session retrieved successfully", true, trainingSession);
    }
    public ResponseBaseDto<TrainingSessionResponseDto> createTrainingSession(TrainingSessionCreateRequestDto trainingSessionDto){
        TrainingSession entity = mapper.toEntity(trainingSessionDto);
        entity.setId(UUID.randomUUID().toString());
        TrainingSession savedEntity = repository.save(entity);
        if (savedEntity == null) {
            return new ResponseBaseDto<>("Failed to create training session", false, null);
        }
        TrainingSessionResponseDto createdTrainingSession = mapper.toDto(savedEntity);
        return new ResponseBaseDto<>("Training session created successfully", true, createdTrainingSession);
    }
    public ResponseBaseDto<TrainingSessionResponseDto> updateTrainingSession(String id, TrainingSessionUpdateRequestDto trainingSessionDto){
        TrainingSession entity = repository.findById(id).orElse(null);
        if (entity == null) {
            return new ResponseBaseDto<>("Training session not found", false, null);
        }
        mapper.updateTrainingSessionFromDto(trainingSessionDto, entity);
        TrainingSessionResponseDto updatedTrainingSession = mapper.toDto(repository.save(entity));
        if (updatedTrainingSession == null) {
            return new ResponseBaseDto<>("Failed to update training session", false, null);
        }
        return new ResponseBaseDto<>("Training session updated successfully", true, updatedTrainingSession);
    }
    public ResponseBaseDto<Void> deleteTrainingSession(String id){
        if (!repository.existsById(id)) {
            return new ResponseBaseDto<>("Training session not found", false, null);
        }
        repository.deleteById(id);
        return new ResponseBaseDto<>("Training session deleted successfully", true, null);
    }
    public ResponseBaseDto<List<TrainingSessionResponseDto>> getTrainingSessionsByClubId(String clubId){
        List<TrainingSessionResponseDto> trainingSessions = mapper.toDtoList(repository.findByClubId(clubId));
        if (trainingSessions.isEmpty()) {
            return new ResponseBaseDto<>("No training sessions found for the specified club", false, null);
        }
        return new ResponseBaseDto<>("Training sessions retrieved successfully", true, trainingSessions);
    }
    public ResponseBaseDto<List<TrainingSessionResponseDto>> getTrainingSessionsByTrainerId(String trainerId){
        List<TrainingSessionResponseDto> trainingSessions = mapper.toDtoList(repository.findByTrainerId(trainerId));
        if (trainingSessions.isEmpty()) {
            return new ResponseBaseDto<>("No training sessions found for the specified trainer", false, null);
        }
        return new ResponseBaseDto<>("Training sessions retrieved successfully", true, trainingSessions);
    }
    public ResponseBaseDto<List<TrainingSessionResponseDto>> getTrainingSessionsByAttendeeId(String attendeeId){
        List<TrainingSessionResponseDto> trainingSessions = mapper.toDtoList(repository.findByAttendeesId(attendeeId));
        if (trainingSessions.isEmpty()) {
            return new ResponseBaseDto<>("No training sessions found for the specified attendee", false, null);
        }
        return new ResponseBaseDto<>("Training sessions retrieved successfully", true, trainingSessions);
    }
    public ResponseBaseDto<String> addAttendeeToTrainingSession(TrainingSessionJoinDto dto){
        TrainingSession trainingSession = repository.findById(dto.getTrainingSessionId()).orElse(null);
        Optional<ClubMembership> attendee = clubMembershipRepository.findById(dto.getAttendeeId());
        if (trainingSession == null) {
            return new ResponseBaseDto<>("Training session not found", false, null);
        }
        if (attendee.isEmpty()) {
            return new ResponseBaseDto<>("Attendee not found", false, null);
        }
        if (trainingSession.getAttendees().contains(attendee.get())) {
            return new ResponseBaseDto<>("Attendee already added to the training session", false, null);
        }
        trainingSession.getAttendees().add(attendee.get());
        TrainingSession updatedTrainingSession = repository.save(trainingSession);
        if (updatedTrainingSession == null) {
            return new ResponseBaseDto<>("Failed to add attendee to training session", false, null);
        }
        return new ResponseBaseDto<>("Attendee added to training session successfully", true, updatedTrainingSession.getId());
    }

    public ResponseBaseDto<String> removeAttendeeFromTrainingSession(TrainingSessionJoinDto dto) {
        TrainingSession trainingSession = repository.findById(dto.getTrainingSessionId()).orElse(null);
        Optional<ClubMembership> attendee = clubMembershipRepository.findById(dto.getAttendeeId());
        if (trainingSession == null) {
            return new ResponseBaseDto<>("Training session not found", false, null);
        }
        if (attendee.isEmpty()) {
            return new ResponseBaseDto<>("Attendee not found", false, null);
        }
        if (!trainingSession.getAttendees().contains(attendee.get())) {
            return new ResponseBaseDto<>("Attendee not found in the training session", false, null);
        }
        trainingSession.getAttendees().remove(attendee.get());
        TrainingSession updatedTrainingSession = repository.save(trainingSession);
        if (updatedTrainingSession == null) {
            return new ResponseBaseDto<>("Failed to remove attendee from training session", false, null);
        }
        return new ResponseBaseDto<>("Attendee removed from training session successfully", true, updatedTrainingSession.getId());
    }
}
