package com.example.thaikv.musicdemo.models;

public class ItemTracks {
    private String nameSong;
    private String artist;
    private long time;

    public ItemTracks(String nameSong, String artist, long time) {
        this.nameSong = nameSong;
        this.artist = artist;
        this.time = time;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
