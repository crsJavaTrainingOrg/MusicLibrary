package hu.adam.sipos.music.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.adam.sipos.music.domain.Track;
import java.util.ArrayList;
import java.util.List;

public class AlbumTest {
    private Album masterOfPuppets;

    @Before
    public void setUp() throws Exception {
        masterOfPuppets = new Album("Master of Puppets", "rock", "1986.03.03", null);
    }
    @Test
    public void AlbumHasEmptyTrackList() {

        List<Track> tracks = masterOfPuppets.getTracks();
        Assert.assertTrue(tracks.isEmpty());
    }

    @Test
    public void addingATrackToTrackListAlbumHasTrackInTrackList() {
        List<Track> tracks = new ArrayList<>();
        tracks.add(new Track("Battery","5:12",1,false));
        masterOfPuppets.setTracks(tracks);
        Assert.assertFalse(masterOfPuppets.getTracks().isEmpty());
        Assert.assertEquals("Battery",masterOfPuppets.getTracks().get(0).getTrackTitle());
        Assert.assertEquals("5:12",masterOfPuppets.getTracks().get(0).getTrackLength());
        Assert.assertEquals(1,masterOfPuppets.getTracks().get(0).getNumberOfTrack());
        Assert.assertEquals(false,masterOfPuppets.getTracks().get(0).isExplicitContent());
    }

    @Test
    public void deletingTrackOfAnAlbumTrackIsNoLongerInAlbum() {
        List<Track> tracks = new ArrayList<>();
        tracks.add(new Track("Battery","5:12",1,false));
        masterOfPuppets.setTracks(tracks);
        masterOfPuppets.deleteTrack("Battery");
        Assert.assertFalse(masterOfPuppets.getTracks().stream().anyMatch(item->item.getTrackTitle().equals("Battery")));

    }

    @Test
    public void searchingForATrackWithACertainTitleReturnsTheSearchedTrack() {
        List <Track> tracks = new ArrayList<>();
        Track battery = new Track("Battery","5:12",1,false);
        tracks.add(battery);
        masterOfPuppets.setTracks(tracks);
        Assert.assertEquals(battery,masterOfPuppets.searchTracks("Battery"));
    }


}
