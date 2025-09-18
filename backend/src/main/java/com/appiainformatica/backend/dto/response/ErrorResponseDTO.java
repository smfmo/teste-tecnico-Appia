package com.appiainformatica.backend.dto.response;

import java.util.List;

public record ErrorResponseDTO(
        int status,
        String message,
        List<FieldErrorResponseDTO> errors
) {}
