package com.appiainformatica.backend.dto.response;

import com.appiainformatica.backend.model.enums.Priority;
import com.appiainformatica.backend.model.enums.Status;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record IncidentResponseDTO(
        UUID id,
        String title,
        String description,
        Priority priority,
        Status status,
        String responsibleEmail,
        List<String> tags,
        LocalDateTime createdDate,
        LocalDateTime updatedDate
) {}
