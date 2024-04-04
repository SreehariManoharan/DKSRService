package org.incidence.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;
import java.util.List;

/**
 * A class representing coordinates. This class is used for deserialization from JSON format.
 */
@JsonDeserialize(using = Coordinate.Deserializer.class)
public class Coordinate {
    public List<Double> doubleArrayValue;
    public Double doubleValue;

    /**
     * A class representing the deserializer for the Coordinate class. This class is used for deserialization from JSON format.
     */
    static class Deserializer extends JsonDeserializer<Coordinate> {
        @Override
        public Coordinate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            Coordinate value = new Coordinate();
            switch (jsonParser.currentToken()) {
                case VALUE_NUMBER_INT:
                case VALUE_NUMBER_FLOAT:
                    value.doubleValue = jsonParser.readValueAs(Double.class);
                    break;
                case START_ARRAY:
                    value.doubleArrayValue = jsonParser.readValueAs(new TypeReference<List>() {});
                    break;
                default: throw new IOException("Cannot deserialize Coordinate");
            }
            return value;
        }
    }
}
