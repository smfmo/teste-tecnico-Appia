package com.appiainformatica.backend.controller;

import com.appiainformatica.backend.service.IncidentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;

@RestController
@RequestMapping("/stats")
public class StatisticsController {

    private final IncidentService incidentService;

    public StatisticsController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @GetMapping("/incidents")
    public ResponseEntity<HashMap<String, Object>> getStats() {
        HashMap<String, Object> stats = new HashMap<>();
        stats.put("Status", incidentService.getStatsByStatus());
        stats.put("Priority", incidentService.getStatsByPriority());
        return ResponseEntity.ok(stats);
    }
}
