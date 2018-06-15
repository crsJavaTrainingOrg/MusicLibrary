package hu.adam.sipos.music.management;

import hu.adam.sipos.music.domain.Album;
import hu.adam.sipos.music.domain.Artist;
import hu.adam.sipos.music.domain.Library;
import hu.adam.sipos.music.domain.Track;
import hu.adam.sipos.music.persistence.ArtistFileRepository;
import hu.adam.sipos.music.persistence.LibraryFileRepository;
import hu.adam.sipos.music.serialization.JsonSerializationService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;

public class LibraryManagementTest {

    private final Path libraryStorePath;
    private final Path artistsInputFilePath;
    LibraryFileRepository libraryFileRepository;
    ArtistFileRepository artistFileRepository;

    {
        libraryStorePath = Paths.get("./build/tmp/library.json");
        artistsInputFilePath = Paths.get("./build/tmp/artists.json");
        libraryFileRepository = new LibraryFileRepository(libraryStorePath, new JsonSerializationService());
        artistFileRepository = new ArtistFileRepository(artistsInputFilePath, new JsonSerializationService());
    }

    @Before
    public void setUp() {
        Library library = new Library();

        Track battery = new Track("Battery", "05:12", false);
        Album masterOfPuppets = new Album("Master of Puppets", "rock", LocalDate.of(1986, 3, 3), null);
        masterOfPuppets.setTracks(Collections.singletonList(battery));
        Artist metallica = new Artist("Metallica");
        metallica.setAlbums(Collections.singletonList(masterOfPuppets));
        library.setArtists(Collections.singletonList(metallica));


        LibraryFileRepository libraryFileRepository = new LibraryFileRepository(libraryStorePath, new JsonSerializationService());
        libraryFileRepository.save(library);
    }

    @After
    public void tearDown() throws Exception {
        Files.delete(libraryStorePath);
        Files.delete(artistsInputFilePath);
    }

    @Test
    @SuppressWarnings("Duplicates")
    public void shouldAddArtistToALibraryFromAFileIfNotExists() {
        Library library = new Library();

        Track battery = new Track("Battery", "05:12", false);
        Album masterOfPuppets = new Album("Master of Puppets", "rock", LocalDate.of(1986, 3, 3), null);
        masterOfPuppets.setTracks(Collections.singletonList(battery));
        Artist metallica = new Artist("Metallica");
        metallica.setAlbums(Collections.singletonList(masterOfPuppets));
        library.setArtists(Collections.singletonList(metallica));


        LibraryFileRepository libraryFileRepository = new LibraryFileRepository(libraryStorePath, new JsonSerializationService());
        libraryFileRepository.save(library);


        Library loadedLibrary = libraryFileRepository.load();

        Track aqualung = new Track("Aqualung", "06:38", false);
        Album aqualungAlbum = new Album("Aqualung", "rock", LocalDate.of(1971, 3, 19), null);
        aqualungAlbum.setTracks(Collections.singletonList(aqualung));
        Artist jethroTull = new Artist("Jethro Tull");
        jethroTull.setAlbums(Collections.singletonList(aqualungAlbum));
        artistFileRepository.save(jethroTull);

        Artist loadedArtist = artistFileRepository.load();

        MusicLibraryManager musicLibraryManager = new MusicLibraryManager(loadedLibrary);
        Library extendedLibrary = musicLibraryManager.addOrOverride(loadedArtist);


        Library expectedLibrary = new Library();
        expectedLibrary.setArtists(Arrays.asList(metallica, jethroTull));

        Assert.assertThat(expectedLibrary, is(extendedLibrary));
    }

    @Test
    @SuppressWarnings("Duplicates")
    public void shouldOverrideArtistInALibraryFromAFileIfExists() {
        Library library = new Library();

        Track battery = new Track("Battery", "05:12", false);
        Album masterOfPuppets = new Album("Master of Puppets", "rock", LocalDate.of(1986, 3, 3), null);
        masterOfPuppets.setTracks(Collections.singletonList(battery));
        Artist metallica = new Artist("Metallica");
        metallica.setAlbums(Collections.singletonList(masterOfPuppets));

        Track aqualung = new Track("Aqualung", "06:38", false);
        Album aqualungAlbum = new Album("Aqualung", "rock", LocalDate.of(1971, 3, 19), null);
        aqualungAlbum.setTracks(Collections.singletonList(aqualung));
        Artist jethroTull = new Artist("Jethro Tull");
        jethroTull.setAlbums(Collections.singletonList(aqualungAlbum));

        library.setArtists(Arrays.asList(metallica, jethroTull));

        libraryFileRepository.save(library);

        Library loadedLibrary = libraryFileRepository.load();

        Track blackened = new Track("Blackened", "06:41", false);
        Album andJusticeForAll = new Album("...And Justice For All", "rock", LocalDate.of(1988, 8, 25), null);
        andJusticeForAll.setTracks(Collections.singletonList(blackened));
        Artist metallica2 = new Artist("Metallica");
        metallica2.setAlbums(Collections.singletonList(andJusticeForAll));

        artistFileRepository.save(metallica2);

        MusicLibraryManager musicLibraryManager = new MusicLibraryManager(loadedLibrary);
        musicLibraryManager.addOrOverride(jethroTull);
        Library extendedLibrary = musicLibraryManager.addOrOverride(metallica2);
        Library expectedLibrary = new Library();

        expectedLibrary.setArtists(Arrays.asList(metallica2, jethroTull));

        Assert.assertThat(expectedLibrary, is(extendedLibrary));
    }
}
