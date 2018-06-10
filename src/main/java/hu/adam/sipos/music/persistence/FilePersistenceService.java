package hu.adam.sipos.music.persistence;

import hu.adam.sipos.music.serialization.SerializationService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilePersistenceService {
    private SerializationService serializationService;

    public FilePersistenceService(SerializationService serializationService){
        this.serializationService = serializationService;
    }

    public void persist(String pathString, Object object) throws IOException{
        Path path = Paths.get(pathString);
        Files.write(path, serializationService.serialize(object).getBytes());
    }
    public String load(String pathString) throws IOException{
        Path path = Paths.get(pathString);
        return new String(Files.readAllBytes(path));
    }

}
