package hu.adam.sipos.music.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Artist {
    private String name;
    private List<Album> albums = new ArrayList<>();

    public Artist(String name) {
        if(name == null){
            throw new IllegalArgumentException("You must specify a name for the artist!");
        }

        this.name = name;
    }

    public void deleteAlbumWithCertainName(String titleOfAlbumToDelete) {
        albums.removeIf(album -> album.getTitleOfAlbum().equals(titleOfAlbumToDelete));
    }

    public Optional<Album> searchAlbumByAlbumTitle(String titleOfSearchedAlbum) {
        return albums.stream()
                .filter(a -> a.getTitleOfAlbum().equals(titleOfSearchedAlbum))
                .findFirst();
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public String getName() {
        return name;
    }
}
