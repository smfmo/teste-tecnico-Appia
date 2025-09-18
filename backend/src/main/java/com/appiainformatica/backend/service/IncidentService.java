package com.appiainformatica.backend.service;

import com.appiainformatica.backend.model.Incident;
import com.appiainformatica.backend.model.enums.Priority;
import com.appiainformatica.backend.model.enums.Status;
import com.appiainformatica.backend.repository.IncidentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;
import static com.appiainformatica.backend.repository.specs.IncidentSpecs.*;

@Service
public class IncidentService {

    private final IncidentRepository repository;

    public IncidentService(IncidentRepository repository) {
        this.repository = repository;
    }

    public Page<Incident> getIncidentsPerFilter(
            Status status, Priority priority, String q,
            Integer page, Integer pageSize, String sort){

        Specification<Incident> specs = Specification
                .allOf((root, query, cb) -> cb.conjunction());
        if (status != null) {
            specs = specs.and(statusEqual(status));
        }
        if (priority != null) {
            specs = specs.and(priorityEqual(priority));
        }
        if (q != null){
            specs = specs.and(titleOrDescriptionContains(q));
        }

        Pageable pageRequest = createPageRequest(page, pageSize, sort);
        return repository.findAll(specs, pageRequest);
    }

    private Pageable createPageRequest(Integer page, Integer pageSize, String sort) {
        if (sort == null || sort.isEmpty()) {
            return PageRequest.of(page, pageSize);
        }
        String[] sortParams = sort.split(",");

        if (sortParams.length != 2) {
            return PageRequest.of(page, pageSize);
        }

        String field = sortParams[0].trim();
        String direction = sortParams[1].trim().toUpperCase();

        Sort.Direction sortDirection = direction.equals("DESC")
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;
        return  PageRequest.of(page, pageSize, Sort.by(sortDirection, field));
    }

    public void saveIncident(Incident incident) {
        repository.save(incident);
    }

    public Optional<Incident> getIncidentById(UUID id) {
        return repository.findById(id);
    }
}