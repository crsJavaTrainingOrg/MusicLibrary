package hu.adam.sipos.music.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AlbumTest {
    @Test
    public void AlbumHasEmptyTrackList() {
        Library library = new Library();
        List<Artist> myArtists = new ArrayList<>();
        myArtists.add(new Artist("Metallica"));
        library.setArtists(myArtists);
        List<Album> myAlbums = new ArrayList<>();
        myAlbums.add(new Album("Master of Puppets","rock","1986.03.03",null));
        library.getArtists().get(0).setAlbums(myAlbums);
        List<Album> albums = library.getArtists().get(0).getAlbums();
        Album albumFromArtistFromLibrary = albums.get(0);
        Assert.assertTrue(albumFromArtistFromLibrary.getTracks().isEmpty());


    }
}
