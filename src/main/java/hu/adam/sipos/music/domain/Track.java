package hu.adam.sipos.music.domain;

public class Track {

    private String trackTitle;
    private String trackLength;
    private int numberOfTrack;
    private boolean isExplicitContent;


    public Track(String trackTitle, String trackLength, int numberOfTrack, boolean isExplicitContent) {
        this.trackTitle = trackTitle;
        this.trackLength = trackLength;
        this.numberOfTrack = numberOfTrack;
        this.isExplicitContent = isExplicitContent;
    }

    public String getTrackTitle() {
        return trackTitle;
    }

    public String getTrackLength() {
        return trackLength;
    }

    public int getNumberOfTrack() {
        return numberOfTrack;
    }

    public boolean isExplicitContent() {
        return isExplicitContent;
    }
}
