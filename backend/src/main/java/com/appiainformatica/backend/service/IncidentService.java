package com.appiainformatica.backend.service;

import com.appiainformatica.backend.model.Incident;
import com.appiainformatica.backend.model.enums.Priority;
import com.appiainformatica.backend.model.enums.Status;
import com.appiainformatica.backend.repository.IncidentRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import static com.appiainformatica.backend.repository.specs.IncidentSpecs.*;

@Service
public class IncidentService {

    private final IncidentRepository repository;

    public IncidentService(IncidentRepository repository) {
        this.repository = repository;
    }

    public Incident saveIncident(Incident incident) {
        return repository.save(incident);
    }

    public List<Incident> getIncidentsPerFilter(Status status, Priority priority, String q){

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
        return repository.findAll(specs);
    }

}