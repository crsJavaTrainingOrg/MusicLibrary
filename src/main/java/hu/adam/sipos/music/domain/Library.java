package hu.adam.sipos.music.domain;

import java.util.ArrayList;
import java.util.List;

public class Library {
    public  List<Artist> artists = new ArrayList<>();
    public List<Playlist> playlists = new ArrayList<>();

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }


    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlist) {
        this.playlists = playlist;
    }

    public Artist searchArtist(String searchedArtist){
        return artists.stream().filter(artist -> artist.getName().equals(searchedArtist)).findFirst().orElse(null);
    }


    public Album searchAlbum(String searchedAlbum) {
        return artists.stream().flatMap(artist -> artist.getAlbums().stream()).filter(album -> album.getTitleOfAlbum().equals(searchedAlbum)).findFirst().orElse(null);
    }

    public Track searchTrack(String searchedTrack) {
        for(Artist artist: artists){
            for(Album album: artist.getAlbums()){
                for (Track track: album.getTracks()){
                    if (track.getTrackTitle().equals(searchedTrack)){
                        return track;
                    }
                }
            }
        }
        return null;
    }
}
