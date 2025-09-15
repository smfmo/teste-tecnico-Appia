package com.appiainformatica.backend.repository.specs;

import com.appiainformatica.backend.model.Incident;
import com.appiainformatica.backend.model.enums.Priority;
import com.appiainformatica.backend.model.enums.Status;
import org.springframework.data.jpa.domain.Specification;

public class IncidentSpecs {

    public static Specification<Incident> statusEqual(Status status){
        return  (root,
                 query,
                 cb) -> cb.equal(root.get("status"), status
        );
    }

    public static Specification<Incident> priorityEqual(Priority priority) {
        return (root,
                query,
                cb) -> cb.equal(root.get("priority"), priority
        );
    }

    public static Specification<Incident> titleOrDescriptionContains(String text) {
        return (root,
                 query,
                 cb) -> {

            String likePattern = "%" + text.toLowerCase() + "%";

            return cb.or(
                    cb.like(cb.lower(root.get("title")), likePattern),
                    cb.like(cb.lower(root.get("description")), likePattern)
            );
        };
    }
}
