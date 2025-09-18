package com.appiainformatica.backend.service;

import com.appiainformatica.backend.model.Comment;
import com.appiainformatica.backend.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final IncidentService incidentService;

    public CommentService(CommentRepository commentRepository,
                          IncidentService incidentService) {
        this.commentRepository = commentRepository;
        this.incidentService = incidentService;
    }

    public Comment saveComment(Comment comment, UUID incidentId) {
        validateIncidentsExists(incidentId);

        comment.setIncidentId(incidentId);
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByIncident(UUID incidentId) {
        validateIncidentsExists(incidentId);
        return commentRepository.findByIncidentId(incidentId);
    }


    private void validateIncidentsExists(UUID incidentId) {
        incidentService.getIncidentById(incidentId)
                .orElseThrow(()->new RuntimeException("incident nao encontrado"));
    }
}
