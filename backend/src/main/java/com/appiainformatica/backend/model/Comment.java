package com.appiainformatica.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "comment")
@Getter @Setter
@EntityListeners(AuditingEntityListener.class)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "incident_id")
    private UUID incidentId;

    @Column(name = "author")
    private String author;

    @Column(name = "message")
    private String message;

    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    public Comment() {}

    public Comment(UUID id, UUID incidentId, String author, String message, LocalDateTime createdDate) {
        this.id = id;
        this.incidentId = incidentId;
        this.author = author;
        this.message = message;
        this.createdDate = createdDate;
    }
}
