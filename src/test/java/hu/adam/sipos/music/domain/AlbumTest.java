package hu.adam.sipos.music.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.adam.sipos.music.domain.Track;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlbumTest {
    private Album masterOfPuppets;

    @Before
    public void setUp() throws Exception {
        masterOfPuppets = new Album("Master of Puppets", "rock", LocalDate.of(1986,03,03), null);
    }
    @Test
    public void AlbumHasEmptyTrackList() {

        List<Track> tracks = masterOfPuppets.getTracks();
        Assert.assertTrue(tracks.isEmpty());
    }

    @Test
    public void addingATrackToTrackListAlbumHasTrackInTrackList() {
        List<Track> tracks = new ArrayList<>();
        tracks.add(new Track("Battery","05:12",false));
        masterOfPuppets.setTracks(tracks);
        Assert.assertFalse(masterOfPuppets.getTracks().isEmpty());
        Assert.assertEquals("Battery",masterOfPuppets.getTracks().get(0).getTrackTitle());
        Assert.assertEquals(312,masterOfPuppets.getTracks().get(0).getTrackLength());
        Assert.assertEquals(false,masterOfPuppets.getTracks().get(0).isExplicitContent());
        Assert.assertEquals("05:12",masterOfPuppets.getTracks().get(0).getTrackLengthString());
    }

    @Test
    public void deletingTrackOfAnAlbumTrackIsNoLongerInAlbum() {
        List<Track> tracks = new ArrayList<>();
        tracks.add(new Track("Battery","05:12",false));
        masterOfPuppets.setTracks(tracks);
        masterOfPuppets.deleteTrack("Battery");
        Assert.assertFalse(masterOfPuppets.getTracks().stream().anyMatch(item->item.getTrackTitle().equals("Battery")));

    }

    @Test
    public void searchingForATrackWithACertainTitleReturnsTheSearchedTrack() {
        List <Track> tracks = new ArrayList<>();
        Track battery = new Track("Battery",312,false);
        tracks.add(battery);
        masterOfPuppets.setTracks(tracks);
        Assert.assertEquals(battery,masterOfPuppets.searchTracks("Battery"));
    }


}
