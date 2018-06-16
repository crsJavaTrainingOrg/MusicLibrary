package hu.adam.sipos.music.track;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class Track {
    private final String trackTitle;
    private final int trackLength;
    private final boolean isExplicitContent;

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
}
