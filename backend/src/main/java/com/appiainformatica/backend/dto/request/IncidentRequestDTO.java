package com.appiainformatica.backend.dto.request;

import com.appiainformatica.backend.model.enums.Priority;
import com.appiainformatica.backend.model.enums.Status;
import jakarta.validation.constraints.*;

import java.util.List;

public record IncidentRequestDTO(
        @NotBlank(message = "Campo obrigatório")
        @Size(min = 5, max = 120, message = "Campo fora do tamanho padrão")
        String title,
        @NotBlank(message = "Campo obrigatório")
        @Size(min = 5, message = "Campo fora do tamanho padrão")
        String description,
        @NotNull(message = "Prioridade é obrigatório")
        Priority priority,
        @NotNull(message = "Status é obrigatório")
        Status status,
        @Email(message = "Email inválido, insira um email válido")
        String responsibleEmail,
        @NotEmpty(message = "tags obrigatórias")
        List<String> tags
) {}
