package org.incidence.kafkalistener;

import org.incidence.IncidentService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CityBoundKafkaListener {
    private final IncidentService incidentService;

    public CityBoundKafkaListener(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @KafkaListener(topics = "citybound-topic", groupId = "group1")
    void cityBoundTopicListener(String cityBounds) {
        incidentService.saveIncidentDetails(cityBounds, "Berlin");
    }
}
