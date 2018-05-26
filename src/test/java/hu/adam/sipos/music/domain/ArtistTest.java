package hu.adam.sipos.music.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArtistTest {
    private Artist metallica;

    @Before
    public void setUp() throws Exception {
        metallica = new Artist("Metallica");
    }

    @Test
    public void artistHasAnEmptyAlbumList() {
        List<Album> albums = metallica.getAlbums();
        Assert.assertTrue(albums.isEmpty());
    }

    @Test
    public void addingAnAlbumToArtistArtistHasAnAlbumInAlbumList() {
        List<Album> metallicaAlbums = new ArrayList<>();
        metallicaAlbums.add(new Album("Master of Puppets", "rock", LocalDate.of(1986,3,3), null));
        metallica.setAlbums(metallicaAlbums);
        Assert.assertFalse(metallica.getAlbums().isEmpty());
        Assert.assertEquals("Master of Puppets", metallica.getAlbums().get(0).getTitleOfAlbum());
        Assert.assertEquals("rock",metallica.getAlbums().get(0).getGenre());
        Assert.assertEquals(LocalDate.of(1986,3,3), metallica.getAlbums().get(0).getFirstReleaseDate());
        Assert.assertNull(metallica.getAlbums().get(0).getNameOfCoStars());
    }

    @Test
    public void deletingAnAlbumOfAnArtistAlbumIsNoLongerInAlbumlist() {
        List<Album> metallicaAlbums = new ArrayList<>();
        metallicaAlbums.add((new Album("Master of Puppets", "rock", LocalDate.of(1986,3,3), null)));
        metallica.setAlbums(metallicaAlbums);
        metallica.deleteAlbumWithCertainName("Master of Puppets");
        Assert.assertFalse(metallica.getAlbums().stream().anyMatch(item -> "Master of Puppets".equals(item.getTitleOfAlbum())));

    }

    @Test
    public void searchingForAnAlbumWithACertainTitleReturnsTheSearchedAlbum() {
        List<Album> metallicaAlbums = new ArrayList<>();
        Album masterOfPuppets = new Album("Master of Puppets", "rock", LocalDate.of(1986,3,3), null);
        metallicaAlbums.add(masterOfPuppets);
        metallica.setAlbums(metallicaAlbums);
        Optional<Album> masterOfPuppetsOptional = metallica.searchAlbumByAlbumTitle("Master of Puppets");
        Assert.assertTrue(masterOfPuppetsOptional.isPresent());
        Assert.assertEquals("Master of Puppets", masterOfPuppetsOptional.get().getTitleOfAlbum());

    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingAnAlbumWithNullAlbumTitleShouldResultIllegalArgumentException() {
        new Album(null, "rock", LocalDate.of(1986,3,3), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingAnAlbumWithZeroLengthStringAsNameOfCoStarsShouldResultIllegalArgumentException() {
        new Album("Master of Puppets", "rock", LocalDate.of(1986,3,3), "");
    }
}
