package hu.adam.sipos.music.playlist;

import hu.adam.sipos.music.library.Library;
import hu.adam.sipos.music.track.Track;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

public class PlaylistTest {
    @Test
    public void addingAPlayListToTheArrayOfPlayListsArrayOfPlayListHasAnElement() {
        Library library = new Library(List.of(), List.of(new Playlist("Car")));
        Assert.assertEquals(1, library.getPlayLists().size());

    }

    @Test
    public void newPlaylistHasEmptyTrackList() {
        Playlist car = new Playlist("Car");
        Assert.assertTrue(car.getTracksOfPlaylist().isEmpty());
    }

    @Test
    @Ignore("Lombok does not generate immutable getters")
    public void playlistIsImmutableAfterCreation() {
        Playlist car = new Playlist("Car");
        List<Track> tracksInCarPlayList = car.getTracksOfPlaylist();
        tracksInCarPlayList.add(new Track("Battery",312,false));
        Assert.assertTrue(car.getTracksOfPlaylist().isEmpty());

    }

    @Test
    public void addingTracksToPlaylistPlaylistIsNotEmptyTrackHasCertainProperties() {
        Track battery = new Track("Battery", 312, false);
        Track masterOfPuppets = new Track("Master of Puppets",400,false);
        Playlist car = new Playlist("Car",battery,masterOfPuppets);
        List<Track> tracksInCarPlayList = car.getTracksOfPlaylist();
        Assert.assertEquals(2,car.getTracksOfPlaylist().size());
        Assert.assertEquals("Battery",tracksInCarPlayList.get(0).getTrackTitle());
        Assert.assertEquals(312,tracksInCarPlayList.get(0).getTrackLength());
        Assert.assertFalse(tracksInCarPlayList.get(0).isExplicitContent());
        Assert.assertEquals("Master of Puppets",tracksInCarPlayList.get(1).getTrackTitle());
        Assert.assertEquals(400,tracksInCarPlayList.get(1).getTrackLength());
        Assert.assertFalse(tracksInCarPlayList.get(1).isExplicitContent());

    }

}
