package hu.adam.sipos.music.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

    @Test
    public void libraryIsSerializedHasValue() throws JsonProcessingException {
        String serialize = library.serialize();
        Assert.assertNotNull(serialize);
        Assert.assertNotSame("", serialize.trim());
    }

    @Test
    public void shouldSerializeEmptyJson() {
        String expectedResult = "{'artists':[]}".replaceAll("\'","\"");
        Assert.assertEquals(expectedResult, library.serialize());
    }

    @Test
    public void shouldSerializeArtist() {
        Track battery = new Track("Battery","05:12",false);
        Album masterOfPuppets = new Album("Master of Puppets", "rock", "1986.03.03", null);
        masterOfPuppets.setTracks(Collections.singletonList(battery));
        Artist metallica = new Artist("Metallica");
        metallica.setAlbums(Collections.singletonList(masterOfPuppets));
        library.setArtists(Collections.singletonList(metallica));
        String expectedResult = ("{'artists':[{'name':'Metallica','albums':[{'titleOfAlbum':'Master of Puppets'," +
                "'genre':'rock','firstReleaseDate':'1986.03.03','nameOfCoStars':null,'tracks':" +
                "[{'trackTitle':'Battery','trackLength':312,'trackLengthString':'05:12'," +
                "'explicitContent':false}]}]}]}").replaceAll("\'","\"");
        Assert.assertEquals(expectedResult, library.serialize());
    }

    @Test
    public void shouldPersistLibrary() throws IOException {
        Track battery = new Track("Battery","05:12",false);
        Album masterOfPuppets = new Album("Master of Puppets", "rock", "1986.03.03", null);
        masterOfPuppets.setTracks(Collections.singletonList(battery));
        Artist metallica = new Artist("Metallica");
        metallica.setAlbums(Collections.singletonList(masterOfPuppets));
        library.setArtists(Collections.singletonList(metallica));
        String expectedResult = ("{'artists':[{'name':'Metallica','albums':[{'titleOfAlbum':'Master of Puppets'," +
                "'genre':'rock','firstReleaseDate':'1986.03.03','nameOfCoStars':null,'tracks':" +
                "[{'trackTitle':'Battery','trackLength':312,'trackLengthString':'05:12'," +
                "'explicitContent':false}]}]}]}").replaceAll("\'","\"");

        String filePath = "build/test/MusicLibrary/library.json";
        library.persist(filePath);
        String libraryJson = library.load(filePath);
        Assert.assertEquals(expectedResult, libraryJson);
    }
}
