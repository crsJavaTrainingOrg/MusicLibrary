package hu.adam.sipos.music.artist;

import hu.adam.sipos.music.album.Album;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ArtistTest {
    @Test
    public void addingAnAlbumToArtistArtistHasAnAlbumInAlbumList() {
        var metallica = new Artist("Metallica", List.of(new Album("Master of Puppets", "rock", LocalDate.of(1986, 3, 3), null, List.of())));
        Assert.assertFalse(metallica.getAlbums().isEmpty());
        Assert.assertEquals("Master of Puppets", metallica.getAlbums().get(0).getTitleOfAlbum());
        Assert.assertEquals("rock",metallica.getAlbums().get(0).getGenre());
        Assert.assertEquals(LocalDate.of(1986,3,3), metallica.getAlbums().get(0).getFirstReleaseDate());
        Assert.assertNull(metallica.getAlbums().get(0).getNameOfCoStars());
    }

    @Test
    public void afterDeletingAnAlbumOfAnArtistTheAlbumIsNoLongerInAlbumList() {
        List<Album> albums = new ArrayList<>();
        albums.add(new Album("Master of Puppets", "rock", LocalDate.of(1986, 3, 3), null, List.of()));

        var metallica = new Artist("Metallica", albums);
        metallica.deleteAlbumWithCertainName("Master of Puppets");
        Assert.assertFalse(metallica.getAlbums().stream().anyMatch(item -> "Master of Puppets".equals(item.getTitleOfAlbum())));

    }

    @Test
    public void searchingForAnAlbumWithACertainTitleReturnsTheSearchedAlbum() {
        var metallica = new Artist("Metallica", List.of(new Album("Master of Puppets", "rock", LocalDate.of(1986, 3, 3), null, List.of())));
        Optional<Album> masterOfPuppetsOptional = metallica.searchAlbumByAlbumTitle("Master of Puppets");
        Assert.assertTrue(masterOfPuppetsOptional.isPresent());
        Assert.assertEquals("Master of Puppets", masterOfPuppetsOptional.get().getTitleOfAlbum());

    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingAnAlbumWithNullAlbumTitleShouldResultIllegalArgumentException() {
        new Album(null, "rock", LocalDate.of(1986,3,3), null, List.of());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingAnAlbumWithZeroLengthStringAsNameOfCoStarsShouldResultIllegalArgumentException() {
        new Album("Master of Puppets", "rock", LocalDate.of(1986,3,3), "", List.of());
    }
}
