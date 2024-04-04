package org.airquality;

import org.airquality.client.AirQualityIndexBoundClient;
import org.airquality.client.AirQualityIndexFeedClient;
import org.airquality.dto.AirQualityIndexBoundDto;
import org.airquality.mapper.AirQualityIndexMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * This class represents a service for retrieving and storing air quality index data.
 */
@Service
public class AirQualityIndexService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final AirQualityIndexBoundClient airQualityIndexBoundClient;
    private final AirQualityIndexFeedClient airQualityIndexFeedClient;

    private final AirQualityIndexMapper airQualityIndexMapper;

    private final AirQualityIndexRepository airQualityIndexRepository;

    public AirQualityIndexService(AirQualityIndexBoundClient airQualityIndexBoundClient, AirQualityIndexFeedClient airQualityIndexFeedClient,
                                  AirQualityIndexMapper airQualityIndexMapper, AirQualityIndexRepository airQualityIndexRepository,
                                  KafkaTemplate<String, String> kafkaTemplate) {
        this.airQualityIndexBoundClient = airQualityIndexBoundClient;
        this.airQualityIndexFeedClient = airQualityIndexFeedClient;
        this.airQualityIndexMapper = airQualityIndexMapper;
        this.airQualityIndexRepository = airQualityIndexRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    private String getBoundInput(List<Double> cityBounds) {
        return cityBounds.stream()
                .map(String::valueOf)
                .reduce((firstLatAndLong, secondLatAndLong) -> firstLatAndLong.concat(",").concat(secondLatAndLong))
                .orElse("");
    }
    public AirQualityIndexBoundDto getAirQualityIndex(String stationId, String token) {
        return Optional.ofNullable(getAllLatsAndLongsFromFeed(stationId, token))
                .map(this::getBoundInput)
                .map(latsLongsInput -> airQualityIndexBoundClient.getAirQualityIndexBound(latsLongsInput, token))
                .map(airQualityIndexMapper::getAirQualityIndex)
                .map(airQualityIndexRepository::save)
                .orElse(new AirQualityIndexBoundDto());
    }

    private List<Double> getAllLatsAndLongsFromFeed(String stationId, String token) {
        return Optional.ofNullable(airQualityIndexFeedClient.getFeed(stationId, token))
                .map(this::getLatitudeAndLongitudeFromCityFeed)
                .orElse(Collections.emptyList());
    }
    private List<Double> getLatitudeAndLongitudeFromCityFeed(LinkedHashMap<String, ?> airQualityFeed) {
        return Optional.ofNullable((LinkedHashMap) airQualityFeed.get("data"))
                .map(airQualityIndexFeedData -> (LinkedHashMap) airQualityIndexFeedData.get("city"))
                .map(cityAirQualityIndex -> (List<Double>) cityAirQualityIndex.get("geo"))
                .map(geo -> {
                    setKafkaTopic(geo);
                    return getExpandedCityBoundValues(geo);
                })
                .orElse(Collections.emptyList());
    }

    /**
     * Sets the Kafka topic with the given geographic coordinates.
     *
     * @param geo the list of latitude and longitude values [latitude, longitude]
     */
    private void setKafkaTopic(List<Double> geo) {
        kafkaTemplate.send("citybound-topic", getBoundInput(getExpandedCityBoundValuesForIncidents(geo)));
    }

    private List<Double> getExpandedCityBoundValues(List<Double> geo) {
       return Arrays.asList(geo.get(0)-0.1, geo.get(1)-0.1, geo.get(0)+0.1 , geo.get(1)+0.1);
    }
    private List<Double> getExpandedCityBoundValuesForIncidents(List<Double> geo) {
        return Arrays.asList(geo.get(1) - 0.1, geo.get(0) - 0.1, geo.get(1) + 0.1 , geo.get(0) + 0.1);
    }
}
