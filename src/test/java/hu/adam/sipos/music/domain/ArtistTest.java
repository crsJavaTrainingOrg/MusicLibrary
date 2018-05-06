package hu.adam.sipos.music.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ArtistTest {
    private Artist metallica;

    @Before
    public void setUp() throws Exception {
        metallica = new Artist("Metallica");
    }

    @Test
    public void ArtistHasAnEmptyAlbumList() {
        List<Album> albums = metallica.getAlbums();
        Assert.assertTrue(albums.isEmpty());
    }


    @Test
    public void AddingAnAlbumToArtistArtistHasAnAlbumInAlbumList() {
        List<Album> metallicaAlbums = new ArrayList<>();
        metallicaAlbums.add(new Album("Master of Puppets", "rock", "1986.03.03", null));
        metallica.setAlbums(metallicaAlbums);
        Assert.assertFalse(metallica.getAlbums().isEmpty());
        Assert.assertEquals("Master of Puppets", metallica.getAlbums().get(0).getTitleOfAlbum());
        Assert.assertEquals("rock",metallica.getAlbums().get(0).getGenre());
        Assert.assertEquals("1986.03.03", metallica.getAlbums().get(0).getFirstReleaseDate());
        Assert.assertNull(metallica.getAlbums().get(0).getNameOfCoStars());
    }

    @Test
    public void deletingAnAlbumOfAnArtistAlbumIsNoLongerInAlbumlist() {
        List<Album> metallicaAlbums = new ArrayList<>();
        metallicaAlbums.add((new Album("Master of Puppets", "rock", "1986.03.03", null)));
        metallica.setAlbums(metallicaAlbums);
        metallica.deleteAlbumWithCertainName("Master of Puppets");
        Assert.assertFalse(metallica.getAlbums().stream().anyMatch(item -> "Master of Puppets".equals(item.getTitleOfAlbum())));

    }

    @Test
    public void searchingForAnAlbumWithACertainTitleReturnsTheSearchedAlbum() {
        List<Album> metallicaAlbums = new ArrayList<>();
        Album masterOfPuppets = new Album("Master of Puppets", "rock", "1986.03.03", null);
        metallicaAlbums.add(masterOfPuppets);
        metallica.setAlbums(metallicaAlbums);
        Assert.assertEquals(masterOfPuppets ,metallica.search("Master of Puppets"));

    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingAnAlbumWithNullAlbumTitleShouldResultIllegalArgumentException() {
        new Album(null, "rock", "1986.03.03", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingAnAlbumWithIllegalFirstReleaseDateFormatShouldResultIllegalArgumentException() {
        new Album("Master of Puppets", "rock", "03.03.1986", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingAnAlbumWithZeroLengthStringAsNameOfCoStarsShouldResultIllegalArgumentException() {
        new Album("Master of Puppets", "rock", "1986.03.03", "");
    }
}
