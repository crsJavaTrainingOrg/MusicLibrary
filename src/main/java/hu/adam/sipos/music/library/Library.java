package hu.adam.sipos.music.library;

import hu.adam.sipos.music.album.Album;
import hu.adam.sipos.music.artist.Artist;
import hu.adam.sipos.music.playlist.Playlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public List<Playlist> getPlayLists() {
        return playLists;
    }

    public void setPlayLists(List<Playlist> playlist) {
        this.playLists = playlist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Library library = (Library) o;
        return Objects.equals(artists, library.artists) &&
                Objects.equals(playLists, library.playLists);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artists, playLists);
    }
}
