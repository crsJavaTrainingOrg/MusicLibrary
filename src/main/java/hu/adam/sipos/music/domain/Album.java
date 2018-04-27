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



    public Album(String titleOfAlbum,  String genre, String firstReleaseDate, String nameOfCoStars) {
        if(titleOfAlbum == null) {
            throw new IllegalArgumentException("You must specify a title for the album!");
        }
        if(DateVerifier(firstReleaseDate)){
            this.firstReleaseDate = firstReleaseDate;
        }
        else{
            throw new IllegalArgumentException("The format of the date is illegal. The firstReleaseDate must be yyyy.mm.dd format");
        }
        this.titleOfAlbum = titleOfAlbum;
        this.genre = genre;

        this.nameOfCoStars = nameOfCoStars;


    }
    private boolean DateVerifier(String firstReleaseDate){
        return firstReleaseDate.matches("([12]\\d{3}\\.(0[1-9]|1[0-2])\\.(0[1-9]|[12]\\d|3[01]))");
    }


    public String getTitleOfAlbum() {
        return titleOfAlbum;
    }



    public String getGenre() {
        return genre;
    }



    public String getFirstReleaseDate() {
        return firstReleaseDate;
    }



    public String getNameOfCoStars() {
        return nameOfCoStars;
    }




}
