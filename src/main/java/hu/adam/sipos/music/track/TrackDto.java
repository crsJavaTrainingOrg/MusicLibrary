package hu.adam.sipos.music.track;

import lombok.Data;

@Data
public class TrackDto {
    private String trackTitle;
    private int trackLength;
    private boolean isExplicitContent;

    public Track toDomain() {
        return new Track(trackTitle, trackLength, isExplicitContent);
    }
}
