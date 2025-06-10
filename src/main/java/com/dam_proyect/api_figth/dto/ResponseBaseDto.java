package com.dam_proyect.api_figth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseBaseDto<T> {
    private String message;
    private boolean success;
    private T data;
}
