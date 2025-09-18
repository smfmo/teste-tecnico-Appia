package com.appiainformatica.backend.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record CommentResponseDTO(
        UUID id,
        UUID incidentId,
        String author,
        String message,
        LocalDateTime createdDate
) {}
