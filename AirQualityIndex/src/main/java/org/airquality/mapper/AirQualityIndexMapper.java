package org.airquality.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.airquality.dto.AirQualityIndexBoundDto;
import org.airquality.exception.AirQualityIndexException;
import org.springframework.stereotype.Component;

/**
 * The AirQualityIndexMapper class is responsible for mapping Air Quality Index data to a bound DTO.
 * This class uses the ObjectMapper from the Jackson library to deserialize the JSON string
 * representing the Air Quality Index data into an instance of the AirQualityIndexBoundDto class.
 */
@Component
public final class AirQualityIndexMapper {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public AirQualityIndexBoundDto getAirQualityIndex(String airQualityIndex) {
        try {
            return objectMapper.readValue(airQualityIndex, AirQualityIndexBoundDto.class);
        } catch (JsonProcessingException e) {
            throw new AirQualityIndexException(e.getMessage());
        }
    }
}
