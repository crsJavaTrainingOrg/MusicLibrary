package hu.adam.sipos.music.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PlaylistTest {
    private Library library = new Library();
    private List<Playlist> playlists = library.getPlaylists();

    @Test
    public void FavouritesIsAlreadyInTheListOfPlaylistsAsTheFirstElement() {
        Assert.assertEquals("Kedvencek",playlists.get(0).getTitleOfPlaylist());
    }

    @Test
    public void addingAPlayListToTheArrayOfPlayListsArrayOfPlayListHasTwoElements() {
        playlists.add(new Playlist("Car"));
        library.setPlaylists(playlists);
        Assert.assertEquals(2,library.getPlaylists().size());

    }

    @Test
    public void newPlaylistHasEmptyTracklist() {
        Playlist car = new Playlist("Car");
        Assert.assertTrue(car.getTracksOfPlaylist().isEmpty());
    }

    @Test
    public void addingTracksToPlaylistPlaylistIsNotEmptyTrackHasCertainProperties() {
        Playlist car = new Playlist("Car");
        List<Track> tracksInCarPlayList = car.getTracksOfPlaylist();
        tracksInCarPlayList.add(new Track("Battery",312,false));
        Assert.assertFalse(tracksInCarPlayList.isEmpty());
        Assert.assertEquals("Battery",tracksInCarPlayList.get(0).getTrackTitle());
        Assert.assertEquals(312,tracksInCarPlayList.get(0).getTrackLength());
        Assert.assertEquals(false,tracksInCarPlayList.get(0).isExplicitContent());

    }
    
}
