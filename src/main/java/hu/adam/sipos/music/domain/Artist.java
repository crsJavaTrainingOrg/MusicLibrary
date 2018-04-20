package hu.adam.sipos.music.domain;

public class Artist {
    private String name;

    public Artist(String name) {
        if(name == null){
            throw new IllegalArgumentException("You must specify a name for the artist!");
        }

        this.name = name;
    }
}
