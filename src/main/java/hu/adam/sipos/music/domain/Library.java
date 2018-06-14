package hu.adam.sipos.music.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Library {
    public List<Artist> artists = new ArrayList<>();
    public List<Playlist> playLists = new ArrayList<>();

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
}
