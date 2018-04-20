package hu.adam.sipos.music.domain;

import java.util.*;
public class Library {
    private List<Album> albums = new ArrayList<>();

    public List<Album> getAlbums() {
        return albums;
    }
    public void addEmptyAlbumToLibrary(){
        Album album = new Album("SomeTitle","SomeName","Rock","1997-06-30","Laszlo", Arrays.asList(new Track()));
        albums.add(album);
    }
    public void addAlbumToLibraryManually(){
        Album album = new Album("","","","","",Arrays.asList(new Track()));

        Scanner userInput = new Scanner(System.in);

        System.out.print("Enter title of album: ");
        album.setTitleOfAlbum(userInput.nextLine());

        System.out.print("Enter name of the artist: ");
        album.setNameOfArtist(userInput.nextLine());

        System.out.print("Enter release date of album: ");

        while(!userInput.hasNext("\\d{4}-\\d{2}-\\d{2}")){
            System.out.println("Invalid date format!");
            userInput.nextLine();
        }
        album.setFirstReleaseDate(userInput.nextLine());

        System.out.print("Enter name of the genre: ");
        album.setGenre(userInput.nextLine());

        System.out.print("Enter name of the Co-stars: ");
        album.setNameOfCoStars(userInput.nextLine());

        albums.add(album);

    }
    public void addAlbumToLibrary(Album albumToBeAdded){

       if(albumToBeAdded.getFirstReleaseDate().matches("\\d{4}-\\d{2}-\\d{2}")) {
           albums.add(albumToBeAdded);
       }
    }
    public void deleteAlbum(Album albumToDelete){
        albums.removeIf(album -> album.getTitleOfAlbum().equals(albumToDelete.getTitleOfAlbum())&&album.getNameOfArtist().equals(albumToDelete.getNameOfArtist()));
    }

}
