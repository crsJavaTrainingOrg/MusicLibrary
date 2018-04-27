package hu.adam.sipos.music.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;

public class ArtistTest {
    private Library library = new Library();
    private Artist artist = new Artist("Metallica");

    @Test
    public void ArtistHasAnEmptyAlbumList() {
        List<Album> albums = artist.getAlbums();
        Assert.assertTrue(albums.isEmpty());
    }

    @Test
    public void ArtistFromLibraryHasEmptyAlbumList() {
        List<Artist> myArtists = new ArrayList<>();
        myArtists.add(new Artist("Metallica"));
        library.setArtists(myArtists);
        List<Album> albums = library.getArtists().get(0).getAlbums();
        Assert.assertTrue(albums.isEmpty());
    }

    @Test
    public void AddingAnAlbumToArtistArtistHasAnAlbumInAlbumList() {
       Library library = new Library();
       List<Artist> myArtists = new ArrayList<>();
       myArtists.add(new Artist("Metallica"));
       library.setArtists(myArtists);
       List<Album> myAlbums = new ArrayList<>();
       myAlbums.add(new Album("Master of Puppets","rock","1986.03.03",""));
       library.getArtists().get(0).setAlbums(myAlbums);
       List<Album> albums = library.getArtists().get(0).getAlbums();
       Assert.assertFalse(albums.isEmpty());


    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingAnAlbumWithNullAlbumTitleShouldResultIllegalArgumentException() {
        new Album(null,"rock","1986.03.03","");
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingAnAlbumWithIllegalFirstReleaseDateFormatShouldResultIllegalArgumentException(){
        new Album("Master of Puppets","rock","03.03.1986","");
    }
}
