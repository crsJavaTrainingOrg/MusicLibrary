package hu.adam.sipos.music.persistence;

import hu.adam.sipos.music.domain.Album;
import hu.adam.sipos.music.domain.Artist;
import hu.adam.sipos.music.domain.Track;
import hu.adam.sipos.music.serialization.JsonSerializationService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Collections;

public class ArtistRepositoryTest {
    private final String FILE_PATH = "./build/tmp/artist.json";

    @After
    public void tearDown() throws Exception {
        Files.delete(Paths.get(FILE_PATH));
    }

    @Test
    public void shouldSaveAndLoadArtist() {
        Track battery = new Track("Battery", "05:12", false);
        Album masterOfPuppets = new Album("Master of Puppets", "rock", LocalDate.of(1986, 3, 3), null);
        masterOfPuppets.setTracks(Collections.singletonList(battery));
        Artist metallica = new Artist("Metallica");
        metallica.setAlbums(Collections.singletonList(masterOfPuppets));

        Path artistStorePath = Paths.get(FILE_PATH);
        ArtistFileRepository artistFileRepository = new ArtistFileRepository(artistStorePath, new JsonSerializationService());
        artistFileRepository.save(metallica);
        Artist loadedArtist = artistFileRepository.load();
        Assert.assertEquals(metallica, loadedArtist);
    }
}
