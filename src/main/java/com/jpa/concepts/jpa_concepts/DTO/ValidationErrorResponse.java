package com.jpa.concepts.jpa_concepts.DTO;

public record ValidationErrorResponse(
        String field,
        String message
) {

}
