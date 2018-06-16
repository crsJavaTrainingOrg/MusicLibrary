package hu.adam.sipos.music.album;

import hu.adam.sipos.music.track.TrackDto;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class AlbumDto {
    private String titleOfAlbum;
    private String genre;
    private LocalDate firstReleaseDate;
    private String nameOfCoStars;
    private List<TrackDto> tracks = new ArrayList<>();

    public Album toDomain() {
        Album album = new Album(titleOfAlbum, genre, firstReleaseDate, nameOfCoStars);
        album.setTracks(tracks.stream()
            .map(TrackDto::toDomain)
            .collect(Collectors.toList()));

        return album;
    }
}
