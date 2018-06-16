package hu.adam.sipos.music.playlist;

import hu.adam.sipos.music.track.Track;
import lombok.Data;

import java.util.List;

@Data
public class PlaylistDto {
    private String titleOfPlaylist;
    private List<Track> tracksOfPlaylist;

    public Playlist toDomain() {
        return new Playlist(titleOfPlaylist, tracksOfPlaylist);
    }

}
