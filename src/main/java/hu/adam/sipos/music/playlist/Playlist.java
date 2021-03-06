package hu.adam.sipos.music.playlist;

import hu.adam.sipos.music.track.Track;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Value
@RequiredArgsConstructor
public class Playlist {
    private final String titleOfPlaylist;
    private final List<Track> tracksOfPlaylist;

    public Playlist(String titleOfPlaylist, Track... track) {
        this.titleOfPlaylist = titleOfPlaylist;
        tracksOfPlaylist = new ArrayList<>(Arrays.asList(track));
    }
}
