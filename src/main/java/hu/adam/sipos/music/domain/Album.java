package hu.adam.sipos.music.domain;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

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
        this.titleOfAlbum = titleOfAlbum;
        this.genre = genre;
        if(nameOfCoStars != null) {
            if (nameOfCoStars.trim().length() == 0) {
                throw new IllegalArgumentException("If there are no CoStars, nameOfCoStars should be null!");
            }
            this.nameOfCoStars = nameOfCoStars;
        }
        this.firstReleaseDate = firstReleaseDate;
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

    public Optional<Track> searchTracks(String titleOfSearchedTrack) {
        return tracks.stream()
                .filter(t -> t.getTrackTitle().equals(titleOfSearchedTrack))
                .findFirst();
    }

    public Optional<Track> searchTracks(Predicate<Track> predicate) {
        return tracks.stream()
                .filter(predicate)
                .findFirst();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return Objects.equals(titleOfAlbum, album.titleOfAlbum) &&
                Objects.equals(genre, album.genre) &&
                Objects.equals(firstReleaseDate, album.firstReleaseDate) &&
                Objects.equals(nameOfCoStars, album.nameOfCoStars) &&
                Objects.equals(tracks, album.tracks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titleOfAlbum, genre, firstReleaseDate, nameOfCoStars, tracks);
    }
}
