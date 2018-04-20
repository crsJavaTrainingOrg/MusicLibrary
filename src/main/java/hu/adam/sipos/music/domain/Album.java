package hu.adam.sipos.music.domain;

import java.util.ArrayList;
import java.util.List;

public class Album {
    private String titleOfAlbum;
    private String nameOfArtist;
    private String genre;
    private String firstReleaseDate;
    private String nameOfCoStars;
    private List<Track> tracks = new ArrayList<>();


    public Album(String titleOfAlbum, String nameOfArtist, String genre, String firstReleaseDate, String nameOfCoStars,List<Track> tracks) {
        this.titleOfAlbum = titleOfAlbum;
        this.nameOfArtist = nameOfArtist;
        this.genre = genre;
        this.firstReleaseDate = firstReleaseDate;
        this.nameOfCoStars = nameOfCoStars;
        this.tracks = tracks;
    }

    public List<Track> getTracks() {
        return tracks ;
    }

    public String getTitleOfAlbum() {
        return titleOfAlbum;
    }

    public void setTitleOfAlbum(String titleOfAlbum) {
        this.titleOfAlbum = titleOfAlbum;
    }

    public String getNameOfArtist() {
        return nameOfArtist;
    }

    public void setNameOfArtist(String nameOfArtist) {
        this.nameOfArtist = nameOfArtist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getFirstReleaseDate() {
        return firstReleaseDate;
    }

    public void setFirstReleaseDate(String firstReleaseDate) {
        this.firstReleaseDate = firstReleaseDate;
    }

    public String getNameOfCoStars() {
        return nameOfCoStars;
    }

    public void setNameOfCoStars(String nameOfCoStars) {
        this.nameOfCoStars = nameOfCoStars;
    }


}
