package com.appiainformatica.backend.exception;

import com.appiainformatica.backend.dto.response.ErrorResponseDTO;
import com.appiainformatica.backend.dto.response.FieldErrorResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public ErrorResponseDTO handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(),
                "Verifique os dados fornecidos. Campos obrigatórios estão ausentes ou inválidos.",
                Collections.emptyList());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    public ErrorResponseDTO handleNotFoundException(EntityNotFoundException e) {
        return new ErrorResponseDTO(HttpStatus.NOT_FOUND.value(),
                "O recurso solicitado não foi encontrado ou não existe.",
                Collections.emptyList());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY) // 422
    public ErrorResponseDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        List<FieldError> fieldErrors = e.getFieldErrors();

        List<FieldErrorResponseDTO> errorsList = fieldErrors
                .stream()
                .map(error -> new FieldErrorResponseDTO(
                        error.getField(), error.getDefaultMessage()))
                .toList();
        return new ErrorResponseDTO(HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Erro de validação.",  errorsList);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    public ErrorResponseDTO handleErrosNaoTratados(RuntimeException e) {
        return new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Ocorreu um erro inesperado",
                Collections.emptyList());
    }


}
