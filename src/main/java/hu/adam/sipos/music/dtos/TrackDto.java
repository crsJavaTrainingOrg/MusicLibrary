package hu.adam.sipos.music.dtos;

import hu.adam.sipos.music.domain.Track;

public class TrackDto {
    private String trackTitle;
    private int trackLength;
    private boolean isExplicitContent;

    public Track toDomain() {
        return new Track(trackTitle, trackLength, isExplicitContent);
    }

    public String getTrackTitle() {
        return trackTitle;
    }

    public void setTrackTitle(String trackTitle) {
        this.trackTitle = trackTitle;
    }

    public int getTrackLength() {
        return trackLength;
    }

    public void setTrackLength(int trackLength) {
        this.trackLength = trackLength;
    }

    public boolean isExplicitContent() {
        return isExplicitContent;
    }

    public void setExplicitContent(boolean explicitContent) {
        isExplicitContent = explicitContent;
    }
}
