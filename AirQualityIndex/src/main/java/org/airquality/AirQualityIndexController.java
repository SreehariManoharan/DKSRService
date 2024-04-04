package org.airquality;

import org.airquality.dto.AirQualityIndexBoundDto;
import org.airquality.dto.Token;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This class represents a REST controller for managing air quality index data.
 */
@RestController
@RequestMapping("/api/airqualityindex")
public class AirQualityIndexController {

    private final AirQualityIndexService airQualityIndexService;

    public AirQualityIndexController(AirQualityIndexService airQualityIndexService) {
        this.airQualityIndexService = airQualityIndexService;
    }

    @PostMapping(value = "/{station}")
    public ResponseEntity<AirQualityIndexBoundDto> getAirQualityIndex(@PathVariable("station") String station, @RequestBody Token token) {
        return new ResponseEntity<>(this.airQualityIndexService.getAirQualityIndex(station, token.getToken()), HttpStatusCode.valueOf(200));
    }
}
