package com.appiainformatica.backend.model;

import com.appiainformatica.backend.model.enums.Priority;
import com.appiainformatica.backend.model.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "incident")
@Getter @Setter
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    private String description;

    private Priority priority;

    private Status status;

    private String responsibleEmail;

    private List<String> tags;

    private LocalDateTime openingDate;

    private LocalDateTime updateDate;

    public Incident() {}

    public Incident(UUID id, String title, String description, Priority priority,
                    Status status, String responsibleEmail, List<String> tags,
                    LocalDateTime openingDate, LocalDateTime updateDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.responsibleEmail = responsibleEmail;
        this.tags = tags;
        this.openingDate = openingDate;
        this.updateDate = updateDate;
    }
}
