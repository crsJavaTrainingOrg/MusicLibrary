package hu.adam.sipos.music.library;

import hu.adam.sipos.music.album.Album;
import hu.adam.sipos.music.artist.Artist;
import hu.adam.sipos.music.artist.ArtistFileRepository;
import hu.adam.sipos.music.serialization.JsonSerializationService;
import hu.adam.sipos.music.track.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;

public class LibraryTest {
    private final Path libraryStorePath;
    private final Path artistsInputFilePath;
    private LibraryFileRepository libraryFileRepository;
    private ArtistFileRepository artistFileRepository;

    {
        libraryStorePath = Paths.get("./build/tmp/library.json");
        artistsInputFilePath = Paths.get("./build/tmp/artists.json");
        libraryFileRepository = new LibraryFileRepository(libraryStorePath, new JsonSerializationService());
        artistFileRepository = new ArtistFileRepository(artistsInputFilePath, new JsonSerializationService());
    }

    @Before
    public void setUp() {

        List<Track> tracks = List.of(new Track("Battery", "05:12", false));
        Album masterOfPuppets = new Album("Master of Puppets", "rock", LocalDate.of(1986, 3, 3), null, tracks);
        Artist metallica = new Artist("Metallica", List.of(masterOfPuppets));
        Library library = new Library(List.of(metallica), List.of());


        LibraryFileRepository libraryFileRepository = new LibraryFileRepository(libraryStorePath, new JsonSerializationService());
        libraryFileRepository.save(library);

    }

    @After
    public void tearDown() throws Exception {
        Files.deleteIfExists(libraryStorePath);
        Files.deleteIfExists(artistsInputFilePath);
    }

    @Test
    public void addAnArtistToTheLibraryLibraryHasAnArtist() {
        Library library = new Library(List.of(new Artist("Metallica", List.of())), List.of());
        Assert.assertFalse(library.getArtists().isEmpty());
        Assert.assertEquals("Metallica", library.getArtists().get(0).getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingArtistWithNullNameShouldResultIllegalArgumentException() {
        new Artist(null, List.of());
    }

    @Test
    public void searchingAnArtistWithCertainNameIfArtistExistsReturnArtistWithSameName() {
        Library library = new Library(List.of(new Artist("Metallica", List.of())), List.of());

        Optional<Artist> metallicaOptional = library.searchArtist("Metallica");
        Assert.assertTrue(metallicaOptional.isPresent());
        Assert.assertEquals("Metallica", metallicaOptional.get().getName());
    }

    @Test
    public void searchingAnAlbumWithCertainTitleIfAlbumExistsReturnAlbumWithSameTitle() {
        List<Album> metallicaAlbums = List.of(new Album("Master of Puppets", "rock", LocalDate.of(1986, 3, 3), null, List.of()));
        Artist metallica = new Artist("Metallica", metallicaAlbums);

        Library library = new Library(List.of(metallica), List.of());
        List<Album> masterOfPuppets = library.searchAlbumByTitle("Master of Puppets");
        Assert.assertEquals(1,  masterOfPuppets.size());
        Assert.assertEquals("Master of Puppets", masterOfPuppets.get(0).getTitleOfAlbum());
    }

    @Test
    public void shouldSurviveEmptyLibraryAdd() {
        Library library = new Library(List.of(), List.of());
        Library extendedLibrary = library.addOrOverride(null);
        Assert.assertEquals(library, extendedLibrary);
    }

    @Test
    public void shouldSurviveNullArtistAdd() {
        Track battery = new Track("Battery", "05:12", false);
        Album masterOfPuppets = new Album("Master of Puppets", "rock", LocalDate.of(1986, 3, 3), null, List.of(battery));
        Artist metallica = new Artist("Metallica", List.of(masterOfPuppets));
        Library library = new Library(List.of(metallica), List.of());

        Library extendedLibrary = library.addOrOverride(null);
        Assert.assertEquals(library, extendedLibrary);
    }

    @Test
    @SuppressWarnings("Duplicates")
    public void shouldAddArtistToALibraryFromAFileIfNotExists() {
        Track battery = new Track("Battery", "05:12", false);
        Album masterOfPuppets = new Album("Master of Puppets", "rock", LocalDate.of(1986, 3, 3), null, List.of(battery));
        Artist metallica = new Artist("Metallica", List.of(masterOfPuppets));

        Library library = new Library(List.of(metallica), List.of());

        LibraryFileRepository libraryFileRepository = new LibraryFileRepository(libraryStorePath, new JsonSerializationService());
        libraryFileRepository.save(library);

        Library loadedLibrary = libraryFileRepository.load();

        Track aqualung = new Track("Aqualung", "06:38", false);
        List<Track> tracks = List.of(aqualung);
        Album aqualungAlbum = new Album("Aqualung", "rock", LocalDate.of(1971, 3, 19), null, tracks);
        Artist jethroTull = new Artist("Jethro Tull", List.of(aqualungAlbum));
        artistFileRepository.save(jethroTull);

        Artist loadedArtist = artistFileRepository.load();

        Library extendedLibrary = loadedLibrary.addOrOverride(loadedArtist);

        Library expectedLibrary = new Library(Arrays.asList(metallica, jethroTull), List.of());

        Assert.assertThat(expectedLibrary, is(extendedLibrary));
    }

    @Test
    @SuppressWarnings("Duplicates")
    public void shouldOverrideArtistInALibraryFromAFileIfExists() {
        Track battery = new Track("Battery", "05:12", false);
        Album masterOfPuppets = new Album("Master of Puppets", "rock", LocalDate.of(1986, 3, 3), null, List.of(battery));
        Artist metallica = new Artist("Metallica", List.of(masterOfPuppets));

        Track aqualung = new Track("Aqualung", "06:38", false);
        List<Track> tracks = List.of(aqualung);
        Album aqualungAlbum = new Album("Aqualung", "rock", LocalDate.of(1971, 3, 19), null, tracks);
        Artist jethroTull = new Artist("Jethro Tull", List.of(aqualungAlbum));

        Library library = new Library(Arrays.asList(metallica, jethroTull), List.of());
        libraryFileRepository.save(library);

        Library loadedLibrary = libraryFileRepository.load();

        Track blackened = new Track("Blackened", "06:41", false);
        List<Track> tracks1 = List.of(blackened);
        Album andJusticeForAll = new Album("...And Justice For All", "rock", LocalDate.of(1988, 8, 25), null, tracks1);
        Artist metallica2 = new Artist("Metallica", List.of(andJusticeForAll));

        artistFileRepository.save(metallica2);

        Artist loadedArtist = artistFileRepository.load();

        Library extendedLibrary = loadedLibrary.addOrOverride(loadedArtist);

        Library expectedLibrary = new Library(Arrays.asList(metallica2, jethroTull), List.of());

        Assert.assertThat(expectedLibrary, is(extendedLibrary));
    }
}
