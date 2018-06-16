package hu.adam.sipos.music.library;

import hu.adam.sipos.music.domain.repository.AbstractRepository;
import hu.adam.sipos.music.serialization.SerializationService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LibraryFileRepository implements AbstractRepository<Library> {

    private final Path libraryStorePath;
    private final SerializationService serializationService;

    public LibraryFileRepository(Path libraryStorePath, SerializationService serializationService){
        this.libraryStorePath = libraryStorePath;
        this.serializationService = serializationService;
    }

    @Override
    public Library load() {
        try {
            String libraryJson = new String(Files.readAllBytes(libraryStorePath));
            LibraryDto libraryDto = serializationService.deserialize(libraryJson, LibraryDto.class);
            return libraryDto.toDomain();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Library library) {
        try {
            Files.write(libraryStorePath, serializationService.serialize(library).getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
