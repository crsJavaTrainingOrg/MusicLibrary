package hu.adam.sipos.music.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.ir.ThrowNode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Library {

    private ObjectMapper objectMapper = new ObjectMapper();

    public  List<Artist> artists = new ArrayList<>();

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public String serialize() {
        String json;
        try {
            json = objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }

    public void persist(String pathString) throws IOException {
        Path path = Paths.get(pathString);
        Files.write(path, serialize().getBytes());
    }

    public String load(String pathString) throws IOException {
        Path path = Paths.get(pathString);
        return new String(Files.readAllBytes(path));
    }
}
