package hu.adam.sipos.music.persistence;

import hu.adam.sipos.music.domain.Artist;
import hu.adam.sipos.music.domain.repository.AbstractRepository;
import hu.adam.sipos.music.dtos.ArtistDto;
import hu.adam.sipos.music.serialization.SerializationService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

//TODO: make repository implementations generic
public class ArtistFileRepository implements AbstractRepository<Artist> {
    private final Path artistStorePath;
    private final SerializationService serializationService;

    public ArtistFileRepository(Path artistStorePath, SerializationService serializationService){
        this.artistStorePath = artistStorePath;
        this.serializationService = serializationService;
    }

    @Override
    public Artist load() {
        try {
            String artistJson = new String(Files.readAllBytes(artistStorePath));
            ArtistDto artistDto = serializationService.deserialize(artistJson, ArtistDto.class);
            return artistDto.toDomain();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Artist artist) {
        try {
            Files.write(artistStorePath, serializationService.serialize(artist).getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
