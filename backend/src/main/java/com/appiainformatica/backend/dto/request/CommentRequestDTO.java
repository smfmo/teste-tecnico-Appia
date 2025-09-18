package com.appiainformatica.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CommentRequestDTO(
        @NotBlank(message = "Campo obrigatório")
        @Size(min = 5, max = 120, message = "Campo fora do tamanho padrão")
        String author,
        @NotBlank(message = "Campo obrigatório")
        @Size(min = 5, message = "Campo fora do tamanho padrão")
        String message
) {}
