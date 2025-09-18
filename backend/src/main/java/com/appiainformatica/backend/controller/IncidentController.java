package com.appiainformatica.backend.controller;

import com.appiainformatica.backend.dto.request.IncidentRequestDTO;
import com.appiainformatica.backend.dto.response.IncidentResponseDTO;
import com.appiainformatica.backend.mapper.IncidentMapper;
import com.appiainformatica.backend.model.Incident;
import com.appiainformatica.backend.model.enums.Priority;
import com.appiainformatica.backend.model.enums.Status;
import com.appiainformatica.backend.service.IncidentService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/incidents")
public class IncidentController {

    private final IncidentService service;
    private final IncidentMapper mapper;

    public IncidentController(IncidentService service, IncidentMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<Incident> saveIncident(@RequestBody IncidentRequestDTO incidentRequestDTO) {
        Incident entitySaved = mapper.toEntity(incidentRequestDTO);
        service.saveIncident(entitySaved);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<IncidentResponseDTO>> getIncidents(
            @RequestParam(value = "status", required = false) Status status,
            @RequestParam(value = "priority", required = false) Priority priority,
            @RequestParam(value = "q", required = false) String q,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "page-size", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "sort", required = false) String sort) {

        Page<Incident> pageResponse = service.getIncidentsPerFilter(status, priority, q, page, pageSize, sort);
        Page<IncidentResponseDTO> result = pageResponse.map(mapper::toDto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<IncidentResponseDTO>> getIncidentById(@PathVariable("id") UUID id) {
        Optional<Incident> response = service.getIncidentById(id);
        Optional<IncidentResponseDTO> result = response.map(mapper::toDto);
        return ResponseEntity.ok(result);
    }
}
