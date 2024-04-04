package org.incidence;

import org.incidence.dto.IncidentDetails;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The IncidentController class is responsible for handling HTTP requests related to incidents.
 *
 * It provides endpoints for retrieving incident details.
 */
@RestController
@RequestMapping("/api/incidence")
public class IncidentController {
    private final IncidentService incidentService;

    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @GetMapping
    public ResponseEntity<IncidentDetails> getIncidence() {
        return new ResponseEntity<>(incidentService.getIncidentDetails(), HttpStatusCode.valueOf(200));
    }
}
