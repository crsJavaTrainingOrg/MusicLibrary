package hu.adam.sipos.music.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonSerializationService implements SerializationService {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String serialize(Object object) {
        String json;
        try {
            json = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }
}
