package com.example.thaikv.musicdemo.models;


import android.net.Uri;

public class AlbumMusicStruct {
    private long id;
    private String name;
    private String artist;
    private String uri;
    private String numberSong;
    private String albArt;
    private Uri uriThumbnail;

    public AlbumMusicStruct() {
    }

    public AlbumMusicStruct(long id, String name, String artist, String uri, String numberSong, String albArt) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.uri = uri;
        this.numberSong = numberSong;
        this.albArt = albArt;
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

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getNumberSong() {
        return numberSong;
    }

    public void setNumberSong(String numberSong) {
        this.numberSong = numberSong;
    }

    public String getAlbArt() {
        return albArt;
    }

    public void setAlbArt(String albArt) {
        this.albArt = albArt;
    }

    public Uri getUriThumbnail() {
        return uriThumbnail;
    }

    public void setUriThumbnail(Uri uriThumbnail) {
        this.uriThumbnail = uriThumbnail;
    }
}
