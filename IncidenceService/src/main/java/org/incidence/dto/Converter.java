
package org.incidence.dto;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;

/**
 * The Converter class provides methods for converting JSON strings to IncidentDetails objects.
 */
public class Converter {
    public static IncidentDetails fromJsonString(String json) throws IOException {
        return getObjectReader().readValue(json);
    }
    private static ObjectReader reader;

    private static void instantiateMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SimpleModule module = new SimpleModule();
        mapper.registerModule(module);
        reader = mapper.readerFor(IncidentDetails.class);
    }

    private static ObjectReader getObjectReader() {
        if (reader == null) instantiateMapper();
        return reader;
    }
}
