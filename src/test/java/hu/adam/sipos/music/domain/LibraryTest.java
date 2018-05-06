package hu.adam.sipos.music.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.IllegalFormatException;
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



    /*    @Test
    public void addingAlbumsToTheEmptyAlbumList() {
        Library library = new Library();
        library.addEmptyAlbumToLibrary();
        Assert.assertThat(library.getAlbums().isEmpty(),is(false));
    }

    @Test
    public void albumsInAlbumListHaveCertainProperties() {
        Library library = new Library();
        List<Album> albums = library.getAlbums();
        Album album = new Album("Test Album","Test Artist","Rock","1997-06-30","Bela", Arrays.asList(new Track()));
        library.addAlbumToLibrary(album);
        Assert.assertEquals("Test Album",albums.get(0).getTitleOfAlbum());
        Assert.assertEquals("Test Artist",albums.get(0).getNameOfArtist());
        Assert.assertEquals("1997-06-30",albums.get(0).getFirstReleaseDate());
        Assert.assertEquals("Rock",albums.get(0).getGenre());
        Assert.assertEquals("Bela",albums.get(0).getNameOfCoStars());

    }

    @Test
    public void deletingAnAlbumWithCertainName() {
        Library library = new Library();
        List<Album> albums = library.getAlbums();
        Album album = new Album("FirstAlbum","Metallica","Heavy Metal","1988-01-01","", Arrays.asList(new Track()));
        Album anotherAlbum = new Album("SecondAlbum","Metallica","Heavy Metal","1988-01-01","", Arrays.asList(new Track()));
        library.addAlbumToLibrary(album);
        library.addAlbumToLibrary(anotherAlbum);
        assertTrue(albums.stream().anyMatch((item->"SecondAlbum".equals(item.getTitleOfAlbum()) && "Metallica".equals(item.getNameOfArtist()))));
        library.deleteAlbum(anotherAlbum);
        assertFalse(albums.stream().anyMatch((item->"SecondAlbum".equals(item.getTitleOfAlbum()) && "Metallica".equals(item.getNameOfArtist()))));

    }

    @Test
    public void shouldCreateAnEmptyAlbum() {
        Album album = new Album("Test Album","Test Artist","Rock","1997-06-30","Bela", Arrays.asList(new Track()));
        Assert.assertNotNull(album);
    }

    @Test
    public void newAlbumHasEmptyTrackList() {
        Album album = new Album("Test Album","Test Artist","Rock","1997-06-30","Bela", Arrays.asList());
        List<Track> tracks = album.getTracks();
        Assert.assertEquals(0,tracks.size());

    }*/
}
