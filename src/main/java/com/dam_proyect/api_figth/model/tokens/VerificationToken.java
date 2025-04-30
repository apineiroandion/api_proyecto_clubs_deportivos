package com.dam_proyect.api_figth.model.tokens;

import com.dam_proyect.api_figth.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.time.LocalDateTime;

@Entity
public class VerificationToken {
    @Id
    private String token;

    @OneToOne
    private User user;
    private LocalDateTime expiryDate;
}
