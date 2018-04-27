package hu.adam.sipos.music.domain;

import java.util.ArrayList;
import java.util.List;

public class Artist {
    private String name;
    private List<Album> albums = new ArrayList<>();

    public Artist(String name) {
        if(name == null){
            throw new IllegalArgumentException("You must specify a name for the artist!");
        }

        this.name = name;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}
