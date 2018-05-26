package hu.adam.sipos.music.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibraryTest {
    private Library library = new Library();
    private Artist metallica;
    private List<Artist> myArtists;

    @Before
    public void setUp() throws Exception {
        metallica = new Artist("Metallica");
        myArtists = new ArrayList<>();
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

}
