package hu.adam.sipos.music.domain;

import java.util.ArrayList;
import java.util.List;

public class Library {
    public  List<Artist> artists = new ArrayList<>();

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

}
