package hu.adam.sipos.music.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;

public class JsonSerializationService implements SerializationService{
    private ObjectMapper objectMapper = new ObjectMapper();
    {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Override
    public String serialize(Object object){
        String json;
        try{
            json = objectMapper.writeValueAsString(object);
        }
        catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
        return json;
    }

    @Override
    //https://github.com/FasterXML/jackson-databind/issues/1490
    public <T> T deserialize(String serialized, Class<T> clazz) {
        try {
            return objectMapper.readValue(serialized, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
