package hu.adam.sipos.music.domain;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Album {
    private String titleOfAlbum;
    private String genre;
    private LocalDate firstReleaseDate;
    private String nameOfCoStars;
    private List<Track> tracks = new ArrayList<>();


    public Album(String titleOfAlbum, String genre, LocalDate firstReleaseDate, String nameOfCoStars) {
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
    private boolean DateVerifier(LocalDate firstReleaseDate){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-dd-MM");
        String formattedString = firstReleaseDate.toString();

        return formattedString.matches("([12]\\d{3}\\.(0[1-9]|1[0-2])\\.(0[1-9]|[12]\\d|3[01]))");
    }


    public String getTitleOfAlbum() {
        return titleOfAlbum;
    }

    public String getGenre() {
        return genre;
    }

    public LocalDate getFirstReleaseDate() {
        return firstReleaseDate;
    }

    public String getNameOfCoStars() {
        return nameOfCoStars;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public void deleteTrack(String titleOfTrackToDelete) {
        tracks.removeIf(track->track.getTrackTitle().equals(titleOfTrackToDelete));
    }

    public Track searchTracks(String titleOfSearchedTrack) {
        for(Track t: tracks){
            if(t.getTrackTitle().equals(titleOfSearchedTrack)){
                return t;
            }
        }
        System.out.println("There is no track with the title:"+titleOfSearchedTrack);
        return null;
    }
}
