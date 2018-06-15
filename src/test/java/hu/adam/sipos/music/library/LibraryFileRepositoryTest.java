package hu.adam.sipos.music.library;

import hu.adam.sipos.music.album.Album;
import hu.adam.sipos.music.artist.Artist;
import hu.adam.sipos.music.track.Track;
import hu.adam.sipos.music.serialization.JsonSerializationService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Collections;

public class LibraryFileRepositoryTest {

    private final String FILE_PATH = "./build/tmp/library.json";

    @After
    public void tearDown() throws Exception {
        Files.delete(Paths.get(FILE_PATH));
    }

    @Test
    public void shouldSaveAndLoadLibrary() {
        Library library = new Library();

        Track battery = new Track("Battery", "05:12", false);
        Album masterOfPuppets = new Album("Master of Puppets", "rock", LocalDate.of(1986, 3, 3), null);
        masterOfPuppets.setTracks(Collections.singletonList(battery));
        Artist metallica = new Artist("Metallica");
        metallica.setAlbums(Collections.singletonList(masterOfPuppets));
        library.setArtists(Collections.singletonList(metallica));

        Path libraryStorePath = Paths.get(FILE_PATH);
        LibraryFileRepository libraryFileRepository = new LibraryFileRepository(libraryStorePath, new JsonSerializationService());
        libraryFileRepository.save(library);
        Library loadedLibrary = libraryFileRepository.load();
        Assert.assertEquals(library, loadedLibrary);
    }

}
