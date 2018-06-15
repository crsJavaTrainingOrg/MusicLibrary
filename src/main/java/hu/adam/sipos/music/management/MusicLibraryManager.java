package hu.adam.sipos.music.management;

import hu.adam.sipos.music.domain.Artist;
import hu.adam.sipos.music.domain.Library;

import java.util.OptionalInt;
import java.util.stream.IntStream;

public class MusicLibraryManager {
    private Library library;

    public MusicLibraryManager(Library library) {
        this.library = library;
    }

    public Library addOrOverride(Artist artist) {
        if (artist == null) {
            return library;
        }

        OptionalInt artistIndexInLibraryOptional = IntStream.range(0, library.artists.size())
                .filter(i -> library.getArtists().get(i).getName().equals(artist.getName()))
                .findFirst();

        if (artistIndexInLibraryOptional.isPresent()) {
            library.getArtists().set(artistIndexInLibraryOptional.getAsInt(), artist);
        } else {
            library.getArtists().add(artist);
        }

        return library;
    }
}
