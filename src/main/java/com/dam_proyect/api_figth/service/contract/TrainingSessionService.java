package com.dam_proyect.api_figth.service.contract;

import com.dam_proyect.api_figth.dto.*;

import java.util.List;

public interface TrainingSessionService {
    ResponseBaseDto<List<TrainingSessionResponseDto>> getAllTrainingSessions();
    ResponseBaseDto<TrainingSessionResponseDto> getTrainingSessionById(String id);
    ResponseBaseDto<TrainingSessionResponseDto> createTrainingSession(TrainingSessionCreateRequestDto trainingSessionDto);
    ResponseBaseDto<TrainingSessionResponseDto> updateTrainingSession(String id, TrainingSessionUpdateRequestDto trainingSessionDto);
    ResponseBaseDto<Void> deleteTrainingSession(String id);
    ResponseBaseDto<List<TrainingSessionResponseDto>> getTrainingSessionsByClubId(String clubId);
    ResponseBaseDto<List<TrainingSessionResponseDto>> getTrainingSessionsByTrainerId(String trainerId);
    ResponseBaseDto<List<TrainingSessionResponseDto>> getTrainingSessionsByAttendeeId(String attendeeId);
    ResponseBaseDto<String> addAttendeeToTrainingSession(TrainingSessionJoinDto dto);
    ResponseBaseDto<String> removeAttendeeFromTrainingSession(TrainingSessionJoinDto dto);
}
