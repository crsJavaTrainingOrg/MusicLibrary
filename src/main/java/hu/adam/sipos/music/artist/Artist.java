package hu.adam.sipos.music.artist;

import hu.adam.sipos.music.album.Album;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Getter
@EqualsAndHashCode
public class Artist {
    private String name;
    private List<Album> albums;

    public Artist(String name, List<Album> albums) {
        if(name == null){
            throw new IllegalArgumentException("You must specify a name for the artist!");
        }

        this.name = name;
        this.albums = albums;
    }

    public void deleteAlbumWithCertainName(String titleOfAlbumToDelete) {
        albums.removeIf(album -> album.getTitleOfAlbum().equals(titleOfAlbumToDelete));
    }

    public Optional<Album> searchAlbumByAlbumTitle(String titleOfSearchedAlbum) {
        return albums.stream()
                .filter(a -> a.getTitleOfAlbum().equals(titleOfSearchedAlbum))
                .findFirst();
    }
}
