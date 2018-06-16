package hu.adam.sipos.music.artist;

import hu.adam.sipos.music.album.AlbumDto;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ArtistDto {
    private String name;
    private List<AlbumDto> albums;

    public Artist toDomain() {
        return new Artist(name, albums.stream()
                .map(AlbumDto::toDomain)
                .collect(Collectors.toList()));
    }
}
