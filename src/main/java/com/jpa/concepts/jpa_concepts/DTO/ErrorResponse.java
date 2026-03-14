package com.jpa.concepts.jpa_concepts.DTO;

import org.springframework.http.HttpStatus;

public record ErrorResponse(
        String message,
        HttpStatus status,
        int statusCode
) {
}
