package hu.adam.sipos.music.album;

import hu.adam.sipos.music.track.TrackDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AlbumDto {
    private String titleOfAlbum;
    private String genre;
    private LocalDate firstReleaseDate;
    private String nameOfCoStars;
    private List<TrackDto> tracks = new ArrayList<>();

    public Album toDomain() {
        Album album = new Album(titleOfAlbum, genre, firstReleaseDate, nameOfCoStars);
        album.setTracks(tracks.stream()
            .map(TrackDto::toDomain)
            .collect(Collectors.toList()));

        return album;
    }

    public String getTitleOfAlbum() {
        return titleOfAlbum;
    }

    public void setTitleOfAlbum(String titleOfAlbum) {
        this.titleOfAlbum = titleOfAlbum;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalDate getFirstReleaseDate() {
        return firstReleaseDate;
    }

    public void setFirstReleaseDate(LocalDate firstReleaseDate) {
        this.firstReleaseDate = firstReleaseDate;
    }

    public String getNameOfCoStars() {
        return nameOfCoStars;
    }

    public void setNameOfCoStars(String nameOfCoStars) {
        this.nameOfCoStars = nameOfCoStars;
    }

    public List<TrackDto> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackDto> tracks) {
        this.tracks = tracks;
    }
}
