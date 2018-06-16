package hu.adam.sipos.music.artist;

import hu.adam.sipos.music.album.Album;
import hu.adam.sipos.music.serialization.JsonSerializationService;
import hu.adam.sipos.music.track.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

public class ArtistRepositoryTest {
    private final String FILE_PATH = "./build/tmp/artist.json";

    @After
    public void tearDown() throws Exception {
        Files.deleteIfExists(Paths.get(FILE_PATH));
    }

    @Test
    public void shouldSaveAndLoadArtist() {
        List<Track> tracks = List.of(new Track("Battery", "05:12", false));
        Album masterOfPuppets = new Album("Master of Puppets", "rock", LocalDate.of(1986, 3, 3), null, tracks);
        Artist metallica = new Artist("Metallica", List.of(masterOfPuppets));

        Path artistStorePath = Paths.get(FILE_PATH);
        ArtistFileRepository artistFileRepository = new ArtistFileRepository(artistStorePath, new JsonSerializationService());
        artistFileRepository.save(metallica);
        Artist loadedArtist = artistFileRepository.load();
        Assert.assertEquals(metallica, loadedArtist);
    }
}
