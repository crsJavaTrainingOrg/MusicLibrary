package hu.adam.sipos.music.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
public class LibraryTest {
    private Library library = new Library();
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
        List<Artist> myArtists = new ArrayList<>();
        myArtists.add(new Artist("Metallica"));
        library.setArtists(myArtists);
        Assert.assertFalse(library.getArtists().isEmpty());
        Assert.assertEquals("Metallica",library.getArtists().get(0).getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingArtistWithNullNameShouldResultIllegalArgumentException() {
        new Artist(null);
    }
}
