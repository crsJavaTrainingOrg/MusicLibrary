package hu.adam.sipos.music.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

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
            throw  new RuntimeException(e);
        }
        return json;
    }
}
