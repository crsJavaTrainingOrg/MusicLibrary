package hu.adam.sipos.music.persistence;

import hu.adam.sipos.music.domain.Album;
import hu.adam.sipos.music.domain.Artist;
import hu.adam.sipos.music.domain.Library;
import hu.adam.sipos.music.domain.Track;
import hu.adam.sipos.music.serialization.JsonSerializationService;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;


    public class PersistenceServiceTest {
        @Test
        public void shouldPersistLibrary() throws IOException {
            Library library = new Library();

            Track battery = new Track("Battery", "05:12", false);
            Album masterOfPuppets = new Album("Master of Puppets", "rock", LocalDate.of(1986, 03, 03), null);
            masterOfPuppets.setTracks(Collections.singletonList(battery));
            Artist metallica = new Artist("Metallica");
            metallica.setAlbums(Collections.singletonList(masterOfPuppets));
            library.setArtists(Collections.singletonList(metallica));
            String expectedResult = ("{'artists':[{'name':'Metallica','albums':[{'titleOfAlbum':'Master of Puppets'," +
                    "'genre':'rock','firstReleaseDate':'1986.03.03','nameOfCoStars':null,'tracks':" +
                    "[{'trackTitle':'Battery','trackLength':312,'trackLengthString':'05:12'," +
                    "'explicitContent':false}]}]}]}").replaceAll("\'", "\"");

            FilePersistenceService filePersistenceService = new FilePersistenceService(new JsonSerializationService());

            String filePath = "build/test/MusicLibrary/library.json";
            filePersistenceService.persist(filePath, library);
            String libraryJson = filePersistenceService.load(filePath);
            Assert.assertEquals(expectedResult, libraryJson);
        }

    }
