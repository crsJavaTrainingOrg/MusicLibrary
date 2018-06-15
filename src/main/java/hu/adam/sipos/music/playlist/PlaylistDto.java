package hu.adam.sipos.music.playlist;

import hu.adam.sipos.music.track.Track;

import java.util.List;

public class PlaylistDto {
    private String titleOfPlaylist;
    private List<Track> tracksOfPlaylist;

    public Playlist toDomain() {
        return new Playlist(titleOfPlaylist, tracksOfPlaylist);
    }


    public String getTitleOfPlaylist() {
        return titleOfPlaylist;
    }

    public void setTitleOfPlaylist(String titleOfPlaylist) {
        this.titleOfPlaylist = titleOfPlaylist;
    }

    public List<Track> getTracksOfPlaylist() {
        return tracksOfPlaylist;
    }

    public void setTracksOfPlaylist(List<Track> tracksOfPlaylist) {
        this.tracksOfPlaylist = tracksOfPlaylist;
    }
}
