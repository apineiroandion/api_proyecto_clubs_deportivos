package com.dam_proyect.api_figth.controller;

import com.dam_proyect.api_figth.dto.*;
import com.dam_proyect.api_figth.service.contract.TrainingSessionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/training-sessions")
public class TrainingSessionController {

    public final TrainingSessionService service;

    public TrainingSessionController(TrainingSessionService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseBaseDto<List<TrainingSessionResponseDto>> getAllTrainingSessions() {
        try {
            return service.getAllTrainingSessions();
        } catch (Exception e) {
            return new ResponseBaseDto<>(
                    "Error retrieving training sessions: " + e.getMessage(),
                    false,
                    null
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseBaseDto<TrainingSessionResponseDto> getTrainingSessionById(@PathVariable String id) {
        try {
            return service.getTrainingSessionById(id);
        } catch (Exception e) {
            return new ResponseBaseDto<>(
                    "Error retrieving training session by ID: " + e.getMessage(),
                    false,
                    null
            );
        }
    }

    @PostMapping
    public ResponseBaseDto<TrainingSessionResponseDto> createTrainingSession(@RequestBody TrainingSessionCreateRequestDto trainingSessionDto) {
        try {
            return service.createTrainingSession(trainingSessionDto);
        } catch (Exception e) {
            return new ResponseBaseDto<>(
                    "Error creating training session: " + e.getMessage(),
                    false,
                    null
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseBaseDto<TrainingSessionResponseDto> updateTrainingSession(@PathVariable String id, @RequestBody TrainingSessionUpdateRequestDto updateDto) {
        try {
            return service.updateTrainingSession(id, updateDto);
        } catch (Exception e) {
            return new ResponseBaseDto<>(
                    "Error updating training session: " + e.getMessage(),
                    false,
                    null
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseBaseDto<Void> deleteTrainingSession(@PathVariable String id) {
        try {
            return service.deleteTrainingSession(id);
        } catch (Exception e) {
            return new ResponseBaseDto<>(
                    "Error deleting training session: " + e.getMessage(),
                    false,
                    null
            );
        }
    }

    @GetMapping("/club/{clubId}")
    public ResponseBaseDto<List<TrainingSessionResponseDto>> getTrainingSessionsByClubId(@PathVariable String clubId) {
        try {
            return service.getTrainingSessionsByClubId(clubId);
        } catch (Exception e) {
            return new ResponseBaseDto<>(
                    "Error retrieving training sessions by club ID: " + e.getMessage(),
                    false,
                    null
            );
        }
    }

    @GetMapping("/trainer/{trainerId}")
    public ResponseBaseDto<List<TrainingSessionResponseDto>> getTrainingSessionsByTrainerId(@PathVariable String trainerId) {
        try {
            return service.getTrainingSessionsByTrainerId(trainerId);
        } catch (Exception e) {
            return new ResponseBaseDto<>(
                    "Error retrieving training sessions by trainer ID: " + e.getMessage(),
                    false,
                    null
            );
        }
    }

    @GetMapping("/attendee/{attendeeId}")
    public ResponseBaseDto<List<TrainingSessionResponseDto>> getTrainingSessionsByAttendeeId(@PathVariable String attendeeId) {
        try {
            return service.getTrainingSessionsByAttendeeId(attendeeId);
        } catch (Exception e) {
            return new ResponseBaseDto<>(
                    "Error retrieving training sessions by attendee ID: " + e.getMessage(),
                    false,
                    null
            );
        }
    }

    @PostMapping("/join")
    public ResponseBaseDto<String> addAttendeeToTrainingSession(@RequestBody TrainingSessionJoinDto dto) {
        try {
            return service.addAttendeeToTrainingSession(dto);
        } catch (Exception e) {
            return new ResponseBaseDto<>(
                    "Error adding attendee to training session: " + e.getMessage(),
                    false,
                    null
            );
        }
    }

    @DeleteMapping("/leave")
    public ResponseBaseDto<String> removeAttendeeFromTrainingSession(@RequestBody TrainingSessionJoinDto dto) {
        try {
            return service.removeAttendeeFromTrainingSession(dto);
        } catch (Exception e) {
            return new ResponseBaseDto<>(
                    "Error removing attendee from training session: " + e.getMessage(),
                    false,
                    null
            );
        }
    }

}
