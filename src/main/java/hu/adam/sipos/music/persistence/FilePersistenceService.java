package hu.adam.sipos.music.persistence;

import hu.adam.sipos.music.serialization.JsonSerializerService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilePersistenceService {

    private JsonSerializerService jsonSerializerService;

    public FilePersistenceService(JsonSerializerService jsonSerializerService) {
        this.jsonSerializerService = jsonSerializerService;
    }

    public void persist(String pathString, Object object) throws IOException {
        Path path = Paths.get(pathString);
        Files.write(path, jsonSerializerService.serialize(object).getBytes());
    }

    public String load(String pathString) throws IOException {
        Path path = Paths.get(pathString);
        return new String(Files.readAllBytes(path));
    }
}
