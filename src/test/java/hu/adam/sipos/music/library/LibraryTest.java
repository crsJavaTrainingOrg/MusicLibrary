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
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;

public class LibraryTest {
    private Library library = new Library();
    private Artist metallica;
    private List<Artist> myArtists;
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
        Library library = new Library();

        Track battery = new Track("Battery", "05:12", false);
        Album masterOfPuppets = new Album("Master of Puppets", "rock", LocalDate.of(1986, 3, 3), null);
        masterOfPuppets.setTracks(Collections.singletonList(battery));
        Artist metallica = new Artist("Metallica");
        metallica.setAlbums(Collections.singletonList(masterOfPuppets));
        library.setArtists(Collections.singletonList(metallica));

        this.metallica = metallica;

        LibraryFileRepository libraryFileRepository = new LibraryFileRepository(libraryStorePath, new JsonSerializationService());
        libraryFileRepository.save(library);

        myArtists = new ArrayList<>();
    }

    @After
    public void tearDown() throws Exception {
        Files.deleteIfExists(libraryStorePath);
        Files.deleteIfExists(artistsInputFilePath);
    }

    @Test
    public void shouldCreateAnEmptyLibrary() {
        Assert.assertNotNull(library);
    }

    @Test
    public void newLibraryHasEmptyArtistList() {
        List<Artist> artists = library.getArtists();
        Assert.assertTrue(artists.isEmpty());
    }

    @Test
    public void addAnArtistToTheLibraryLibraryHasAnArtist() {
        myArtists.add(new Artist("Metallica"));
        library.setArtists(myArtists);
        Assert.assertFalse(library.getArtists().isEmpty());
        Assert.assertEquals("Metallica", library.getArtists().get(0).getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingArtistWithNullNameShouldResultIllegalArgumentException() {
        new Artist(null);
    }

    @Test
    public void searchingAnArtistWithCertainNameIfArtistExistsReturnArtistWithSameName() {
        myArtists.add(metallica);
        library.setArtists(myArtists);

        Optional<Artist> metallicaOptional = library.searchArtist("Metallica");
        Assert.assertTrue(metallicaOptional.isPresent());
        Assert.assertEquals("Metallica", metallicaOptional.get().getName());
    }

    @Test
    public void searchingAnAlbumWithCertainTitleIfAlbumExistsReturnAlbumWithSameTitle() {
        myArtists.add(metallica);
        library.setArtists(myArtists);
        List<Album> metallicaAlbums = new ArrayList<>();
        metallicaAlbums.add(new Album("Master of Puppets", "rock", LocalDate.of(1986, 3, 3), null));
        metallica.setAlbums(metallicaAlbums);
        List<Album> masterOfPuppets = library.searchAlbumByTitle("Master of Puppets");
        Assert.assertEquals(1,  masterOfPuppets.size());
        Assert.assertEquals("Master of Puppets", masterOfPuppets.get(0).getTitleOfAlbum());
    }

    @Test
    public void shouldSurviveEmptyLibraryAdd() {
        Library library = new Library();
        Library extendedLibrary = library.addOrOverride(null);
        Assert.assertEquals(library, extendedLibrary);
    }

    @Test
    public void shouldSurviveNullArtistAdd() {
        Library library = new Library();
        Track battery = new Track("Battery", "05:12", false);
        Album masterOfPuppets = new Album("Master of Puppets", "rock", LocalDate.of(1986, 3, 3), null);
        masterOfPuppets.setTracks(Collections.singletonList(battery));
        Artist metallica = new Artist("Metallica");
        metallica.setAlbums(Collections.singletonList(masterOfPuppets));
        library.setArtists(Collections.singletonList(metallica));

        Library extendedLibrary = library.addOrOverride(null);
        Assert.assertEquals(library, extendedLibrary);
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

        Library extendedLibrary = loadedLibrary.addOrOverride(loadedArtist);


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

        Artist loadedArtist = artistFileRepository.load();

        Library extendedLibrary = loadedLibrary.addOrOverride(loadedArtist);

        Library expectedLibrary = new Library();
        expectedLibrary.setArtists(Arrays.asList(metallica2, jethroTull));

        Assert.assertThat(expectedLibrary, is(extendedLibrary));
    }
}
