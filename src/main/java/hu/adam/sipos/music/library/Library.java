package hu.adam.sipos.music.library;

import hu.adam.sipos.music.album.Album;
import hu.adam.sipos.music.artist.Artist;
import hu.adam.sipos.music.playlist.Playlist;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
@Setter
@EqualsAndHashCode
public class Library {
    public List<Artist> artists = new ArrayList<>();
    public List<Playlist> playLists = new ArrayList<>();

    public Optional<Artist> searchArtist(String searchedArtist) {
        return artists.stream()
                .filter(a -> a.getName().equals(searchedArtist))
                .findFirst();
    }

    public List<Album> searchAlbumByTitle(String searchedAlbumTitle) {
        return artists.stream()
                .map(artist -> artist.searchAlbumByAlbumTitle(searchedAlbumTitle))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public Library addOrOverride(Artist artist) {
        if (artist == null) {
            return this;
        }

        OptionalInt artistIndexInLibraryOptional = IntStream.range(0, artists.size())
                .filter(i -> artists.get(i).getName().equals(artist.getName()))
                .findFirst();

        if (artistIndexInLibraryOptional.isPresent()) {
            artists.set(artistIndexInLibraryOptional.getAsInt(), artist);
        } else {
            artists.add(artist);
        }

        return this;
    }
}
