package hu.adam.sipos.music.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Playlist {
    private final String titleOfPlaylist;
    private final List<Track> tracksOfPlaylist;

    public Playlist(String titleOfPlaylist, Track... track) {
        this.titleOfPlaylist = titleOfPlaylist;
        tracksOfPlaylist = new ArrayList<>(Arrays.asList(track));
    }

    public Playlist(String titleOfPlaylist, List<Track> tracksOfPlaylist) {
        this.titleOfPlaylist = titleOfPlaylist;
        this.tracksOfPlaylist = tracksOfPlaylist;
    }

    public String getTitleOfPlaylist() {
        return titleOfPlaylist;
    }

    public List<Track> getTracksOfPlaylist() {
        return new ArrayList<>(tracksOfPlaylist);
    }
}
