package hu.adam.sipos.music.domain;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String titleOfPlaylist;
    private List<Track> tracksOfPlaylist = new ArrayList<>();

    public Playlist(String titleOfPlaylist) {
        this.titleOfPlaylist = titleOfPlaylist;
    }

    public String getTitleOfPlaylist() {
        return titleOfPlaylist;
    }

    public List<Track> getTracksOfPlaylist() {
        return tracksOfPlaylist;
    }
}
