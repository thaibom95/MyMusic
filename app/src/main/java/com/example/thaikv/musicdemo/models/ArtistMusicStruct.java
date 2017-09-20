package com.example.thaikv.musicdemo.models;


public class ArtistMusicStruct {
    private long id;
    private String name;
    private long numberSongOfArtist;
    private long numberAlbumOfArtist;

    public ArtistMusicStruct() {
    }

    public ArtistMusicStruct(long id, String name, long numberSongOfArtist, long numberAlbumOfArtist) {
        this.id = id;
        this.name = name;
        this.numberSongOfArtist = numberSongOfArtist;
        this.numberAlbumOfArtist = numberAlbumOfArtist;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNumberSongOfArtist() {
        return numberSongOfArtist;
    }

    public void setNumberSongOfArtist(long numberSongOfArtist) {
        this.numberSongOfArtist = numberSongOfArtist;
    }

    public long getNumberAlbumOfArtist() {
        return numberAlbumOfArtist;
    }

    public void setNumberAlbumOfArtist(long numberAlbumOfArtist) {
        this.numberAlbumOfArtist = numberAlbumOfArtist;
    }
}
