package org.airquality.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedHashMap;

/**
 * This interface represents a client for retrieving city feed data from a REST API.
 */
@FeignClient(name = "air-quality-index-feed-client", url = "http://api.waqi.info/feed")
public interface AirQualityIndexFeedClient {
    @RequestMapping(value = "/{stationId}", method = RequestMethod.GET)
    LinkedHashMap<String, ?> getFeed(@PathVariable String stationId, @RequestParam(value = "token") String token);
}
