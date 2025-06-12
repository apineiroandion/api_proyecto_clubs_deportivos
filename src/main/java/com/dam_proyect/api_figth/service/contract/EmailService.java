package com.dam_proyect.api_figth.service.contract;

public interface EmailService {
    void sendEmail(String to, String subject, String text);
}
