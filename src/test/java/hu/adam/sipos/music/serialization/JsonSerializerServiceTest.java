package hu.adam.sipos.music.serialization;

import hu.adam.sipos.music.domain.Album;
import hu.adam.sipos.music.domain.Artist;
import hu.adam.sipos.music.domain.Library;
import hu.adam.sipos.music.domain.Track;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

public class JsonSerializerServiceTest {
    private Library library = new Library();

    @Test
    public void libraryIsSerializedHasValue() {
        JsonSerializerService jsonSerializerService = new JsonSerializerService();

        String serialize = jsonSerializerService.serialize(library);
        Assert.assertNotNull(serialize);
        Assert.assertNotSame("", serialize.trim());
    }

    @Test
    public void shouldSerializeEmptyJson() {
        JsonSerializerService jsonSerializerService = new JsonSerializerService();

        String expectedResult = "{'artists':[]}".replaceAll("\'","\"");
        Assert.assertEquals(expectedResult, jsonSerializerService.serialize(library));
    }

    @Test
    public void shouldSerializeArtist() {
        JsonSerializerService jsonSerializerService = new JsonSerializerService();

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

        Assert.assertEquals(expectedResult, jsonSerializerService.serialize(library));
    }
}