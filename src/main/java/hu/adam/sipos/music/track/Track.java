package hu.adam.sipos.music.track;

import java.util.Objects;

public class Track {
    private String trackTitle;
    private int trackLength;
    private boolean isExplicitContent;

    public Track(String trackTitle, int trackLength, boolean isExplicitContent) {
        this.trackTitle = trackTitle;
        this.trackLength = trackLength;
        this.isExplicitContent = isExplicitContent;
    }

    public Track(String trackTitle, String trackLength, boolean isExplicitContent) {
        String[] split = trackLength.split(":");
        int seconds = Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
        this.trackTitle = trackTitle;
        this.trackLength = seconds;
        this.isExplicitContent = isExplicitContent;
    }

    public String asTrackLengthString() {
        return String.format("%02d:%02d",trackLength / 60, trackLength % 60);
    }

    public String getTrackTitle() {
        return trackTitle;
    }

    public int getTrackLength() {
        return trackLength;
    }

    public boolean isExplicitContent() {
        return isExplicitContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return trackLength == track.trackLength &&
                isExplicitContent == track.isExplicitContent &&
                Objects.equals(trackTitle, track.trackTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trackTitle, trackLength, isExplicitContent);
    }
}
