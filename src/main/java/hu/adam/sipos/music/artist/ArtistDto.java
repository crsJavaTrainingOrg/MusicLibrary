package hu.adam.sipos.music.artist;

import hu.adam.sipos.music.album.AlbumDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
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
}
