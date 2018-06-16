package hu.adam.sipos.music.serialization;

import hu.adam.sipos.music.album.Album;
import hu.adam.sipos.music.artist.Artist;
import hu.adam.sipos.music.library.Library;
import hu.adam.sipos.music.library.LibraryDto;
import hu.adam.sipos.music.track.Track;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Collections;


public class JsonSerializationServiceTest {
    @Test
    public void shouldSerializeEmptyJson() {
        SerializationService serializationService = new JsonSerializationService();

        String expectedResult = "{'artists':[],'playLists':[]}".replaceAll("\'", "\"");
        Assert.assertEquals(expectedResult, serializationService.serialize(new Library(Collections.emptyList(), Collections.emptyList())));
    }

    @Test
    public void shouldSerializeAndDeserializeLibrary() {
        SerializationService serializationService = new JsonSerializationService();

        Track battery = new Track("Battery", "05:12", false);
        Album masterOfPuppets = new Album("Master of Puppets", "rock", LocalDate.of(1986, 3, 3), null, Collections.singletonList(battery));
        Artist metallica = new Artist("Metallica", Collections.singletonList(masterOfPuppets));
        Library library = new Library(Collections.singletonList(metallica), Collections.emptyList());

        String serializedLibrary = ("{'artists':[{'name':'Metallica','albums':[{'titleOfAlbum':'Master of Puppets'," +
                "'genre':'rock','firstReleaseDate':'1986-03-03','nameOfCoStars':null,'tracks':" +
                "[{'trackTitle':'Battery','trackLength':312," +
                "'explicitContent':false}]}]}],'playLists':[]}").replaceAll("\'", "\"");


        Assert.assertEquals(serializedLibrary, serializationService.serialize(library));
        Assert.assertEquals(library, (serializationService.deserialize(serializedLibrary, LibraryDto.class)).toDomain());

    }
}

