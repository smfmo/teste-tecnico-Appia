package com.appiainformatica.backend.controller;

import com.appiainformatica.backend.dto.request.CommentRequestDTO;
import com.appiainformatica.backend.dto.request.IncidentRequestDTO;
import com.appiainformatica.backend.dto.response.CommentResponseDTO;
import com.appiainformatica.backend.dto.response.IncidentResponseDTO;
import com.appiainformatica.backend.mapper.CommentMapper;
import com.appiainformatica.backend.mapper.IncidentMapper;
import com.appiainformatica.backend.model.Comment;
import com.appiainformatica.backend.model.Incident;
import com.appiainformatica.backend.model.enums.Priority;
import com.appiainformatica.backend.model.enums.Status;
import com.appiainformatica.backend.service.CommentService;
import com.appiainformatica.backend.service.IncidentService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/incidents")
public class IncidentController {

    private final IncidentService incidentService;
    private final IncidentMapper incidentMapper;
    private final CommentService commentService;
    private final CommentMapper commentMapper;

    public IncidentController(IncidentService incidentService, IncidentMapper incidentMapper,
                              CommentService commentService, CommentMapper commentMapper) {
        this.incidentService = incidentService;
        this.incidentMapper = incidentMapper;
        this.commentService = commentService;
        this.commentMapper = commentMapper;
    }

    @GetMapping
    public ResponseEntity<Page<IncidentResponseDTO>> getIncidentsPerFilter(
            @RequestParam(value = "status", required = false) Status status,
            @RequestParam(value = "priority", required = false) Priority priority,
            @RequestParam(value = "q", required = false) String q,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "page-size", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "sort", required = false) String sort) {

        Page<Incident> pageResponse = incidentService.getIncidentsPerFilter(status, priority, q, page, pageSize, sort);
        Page<IncidentResponseDTO> result = pageResponse.map(incidentMapper::toDto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<IncidentResponseDTO>> getIncidentById(@PathVariable("id") UUID id) {
        Optional<Incident> response = incidentService.getIncidentById(id);
        Optional<IncidentResponseDTO> result = response.map(incidentMapper::toDto);
        return ResponseEntity.ok(result);
    }

}
