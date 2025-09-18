package com.appiainformatica.backend.dto.response;

public record FieldErrorResponseDTO(
    String field,
    String message
) {}
