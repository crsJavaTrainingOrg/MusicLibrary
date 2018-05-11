package hu.adam.sipos.music.domain;

public class Track {

    private String trackTitle;
    private int trackLength;
    private boolean isExplicitContent;
    private String trackLengthString;


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

    public String getTrackTitle() {
        return trackTitle;
    }

    public int getTrackLength() {
        return trackLength;
    }

    public boolean isExplicitContent() {
        return isExplicitContent;
    }

    public String getTrackLengthString() {
        return String.format("%02d:%02d",trackLength / 60, trackLength % 60);



    }
}
