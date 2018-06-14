package hu.adam.sipos.music.serialization;

import hu.adam.sipos.music.domain.Album;
import hu.adam.sipos.music.domain.Artist;
import hu.adam.sipos.music.domain.Library;
import hu.adam.sipos.music.domain.Track;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Collections;


public class JsonSerializationServiceTest {
    private Library library = new Library();

    @Test
    public void libraryIsSerializedHasValue() {
        SerializationService serializationService = new JsonSerializationService();

        String serialize = serializationService.serialize(library);
        Assert.assertNotNull(serialize);
        Assert.assertNotSame("", serialize.trim());
    }

    @Test
    public void shouldSerializeEmptyJson() {
        SerializationService serializationService = new JsonSerializationService();

        String expectedResult = "{'artists':[],'playLists':[]}".replaceAll("\'", "\"");
        Assert.assertEquals(expectedResult, serializationService.serialize(library));
    }

    @Test
    public void shouldSerializeArtist() {
        SerializationService serializationService = new JsonSerializationService();

        Track battery = new Track("Battery", "05:12", false);
        Album masterOfPuppets = new Album("Master of Puppets", "rock", LocalDate.of(1986, 3, 3), null);
        masterOfPuppets.setTracks(Collections.singletonList(battery));
        Artist metallica = new Artist("Metallica");
        metallica.setAlbums(Collections.singletonList(masterOfPuppets));
        library.setArtists(Collections.singletonList(metallica));
        String expectedResult = ("{'artists':[{'name':'Metallica','albums':[{'titleOfAlbum':'Master of Puppets'," +
                "'genre':'rock','firstReleaseDate':'1986-03-03','nameOfCoStars':null,'tracks':" +
                "[{'trackTitle':'Battery','trackLength':312,'trackLengthString':'05:12'," +
                "'explicitContent':false}]}]}],'playLists':[]}").replaceAll("\'", "\"");

        Assert.assertEquals(expectedResult, serializationService.serialize(library));
    }
}

