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
        for(Artist a: artists){
            if(a.getName().equals(searchedArtist)){
                return a;
            }
        }
        return null;
    }


    public Album searchAlbum(String searchedAlbum) {
        for(Artist artist: artists){
            for(Album album: artist.getAlbums()){
                if(album.getTitleOfAlbum().equals(searchedAlbum)){
                    return album;
                }
            }
        }
        return null;
    }
}
