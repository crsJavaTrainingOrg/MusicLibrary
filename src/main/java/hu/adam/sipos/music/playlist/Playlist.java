package hu.adam.sipos.music.playlist;

import hu.adam.sipos.music.track.Track;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Playlist playlist = (Playlist) o;
        return Objects.equals(titleOfPlaylist, playlist.titleOfPlaylist) &&
                Objects.equals(tracksOfPlaylist, playlist.tracksOfPlaylist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titleOfPlaylist, tracksOfPlaylist);
    }
}
