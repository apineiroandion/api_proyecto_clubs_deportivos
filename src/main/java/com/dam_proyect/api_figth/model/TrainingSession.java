package com.dam_proyect.api_figth.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "training_sessions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingSession {
    @Id
    private String id;
    private String title;
    private String description;
    private LocalDate date;

    @ManyToOne
    private ClubMembership trainer;

    @ManyToOne
    private Club club;

    @ManyToMany
    private List<ClubMembership> attendees;
}
