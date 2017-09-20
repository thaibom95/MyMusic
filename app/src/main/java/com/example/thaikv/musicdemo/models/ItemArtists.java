package com.example.thaikv.musicdemo.models;

public class ItemArtists {
    private String artist;
    private int countAlbum;
    private int countTrack;

    public ItemArtists(String artist, int countAlbum, int countTrack) {
        this.artist = artist;
        this.countAlbum = countAlbum;
        this.countTrack = countTrack;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getCountAlbum() {
        return countAlbum;
    }

    public void setCountAlbum(int countAlbum) {
        this.countAlbum = countAlbum;
    }

    public int getCountTrack() {
        return countTrack;
    }

    public void setCountTrack(int countTrack) {
        this.countTrack = countTrack;
    }
}
