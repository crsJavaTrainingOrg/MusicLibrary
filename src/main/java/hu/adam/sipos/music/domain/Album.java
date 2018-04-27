package hu.adam.sipos.music.domain;

import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.regex.Pattern;

public class Album {
    private String titleOfAlbum;
    private String genre;
    private String firstReleaseDate;
    private String nameOfCoStars;
    private List<Track> tracks = new ArrayList<>();



    public Album(String titleOfAlbum,  String genre, String firstReleaseDate, String nameOfCoStars) {
        if(titleOfAlbum == null) {
            throw new IllegalArgumentException("You must specify a title for the album!");
        }
        else{
            this.titleOfAlbum = titleOfAlbum;
        }
        if(DateVerifier(firstReleaseDate)){
            this.firstReleaseDate = firstReleaseDate;
        }
        else{
            throw new IllegalArgumentException("The format of the date is illegal. The firstReleaseDate must be yyyy.mm.dd format");
        }

        this.genre = genre;
        if(nameOfCoStars ==null){
            this.nameOfCoStars = nameOfCoStars;
        }
        else if (nameOfCoStars.length() == 0){
            throw new IllegalArgumentException("If there are no CoStars, nameOfCoStars should be null!");
        }


    }
    private boolean DateVerifier(String firstReleaseDate){
        return firstReleaseDate.matches("([12]\\d{3}\\.(0[1-9]|1[0-2])\\.(0[1-9]|[12]\\d|3[01]))");
    }

    public List<Track> getTracks() {
        return tracks;
    }
}
