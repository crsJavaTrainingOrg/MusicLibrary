package hu.adam.sipos.music.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlbumTest {
    private Album masterOfPuppets;
    private Artist metallica;
    @Before
    public void setUp() throws Exception {
        metallica = new Artist("Metallica");
        masterOfPuppets = new Album("Master of Puppets","rock", LocalDate.of(1986,3,3),null);
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
        Assert.assertFalse(masterOfPuppets.getTracks().get(0).isExplicitContent());
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
        Optional<Track> batteryOptional = masterOfPuppets.searchTracks("Battery");
        Assert.assertTrue(batteryOptional.isPresent());
        Assert.assertEquals("Battery", batteryOptional.get().getTrackTitle());
    }

    @Test
    public void searchingForATrackWithLambdaTrackTitleReturnsTheSearchedTrack() {
        List <Track> tracks = new ArrayList<>();
        Track battery = new Track("Battery",312,false);
        tracks.add(battery);
        masterOfPuppets.setTracks(tracks);
        Optional<Track> batteryOptional = masterOfPuppets.searchTracks(track -> track.getTrackTitle().equals("Battery"));
        Assert.assertTrue(batteryOptional.isPresent());
        Assert.assertEquals("Battery", batteryOptional.get().getTrackTitle());
    }


}
