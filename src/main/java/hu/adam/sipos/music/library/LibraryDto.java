package hu.adam.sipos.music.library;

import hu.adam.sipos.music.artist.ArtistDto;
import hu.adam.sipos.music.playlist.PlaylistDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class LibraryDto {
    public List<ArtistDto> artists = new ArrayList<>();
    public List<PlaylistDto> playLists = new ArrayList<>();

    public Library toDomain() {
        return new Library(artists.stream()
                .map(ArtistDto::toDomain)
                .collect(Collectors.toList()), playLists.stream()
                        .map(PlaylistDto::toDomain)
                        .collect(Collectors.toList()));
    }
}
