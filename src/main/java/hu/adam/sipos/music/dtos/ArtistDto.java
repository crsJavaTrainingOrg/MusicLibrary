package hu.adam.sipos.music.dtos;

import hu.adam.sipos.music.domain.Artist;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArtistDto {
    private String name;
    private List<AlbumDto> albums = new ArrayList<>();

    public Artist toDomain() {
        Artist artist = new Artist(name);
        artist.setAlbums(albums.stream()
                .map(AlbumDto::toDomain)
                .collect(Collectors.toList()));

        return artist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AlbumDto> getAlbums() {
        return albums;
    }

    public void setAlbums(List<AlbumDto> albums) {
        this.albums = albums;
    }
}
