package hu.adam.sipos.music.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class Library {

    ObjectMapper objectMapper = new ObjectMapper();

    public  List<Artist> artists = new ArrayList<>();

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public String serialize() throws JsonProcessingException {
        return objectMapper.writeValueAsString(this);
    }
}
