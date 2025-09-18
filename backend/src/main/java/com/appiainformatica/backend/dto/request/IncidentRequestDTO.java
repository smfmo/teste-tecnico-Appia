package com.appiainformatica.backend.dto.request;

import com.appiainformatica.backend.model.enums.Priority;
import com.appiainformatica.backend.model.enums.Status;
import java.util.List;

public record IncidentRequestDTO(
        String title,
        String description,
        Priority priority,
        Status status,
        String responsibleEmail,
        List<String> tags
) {}
