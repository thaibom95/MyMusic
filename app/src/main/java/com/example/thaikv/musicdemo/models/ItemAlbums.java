package com.example.thaikv.musicdemo.models;

public class ItemAlbums {
    private String nameAlbum;
    private String artist;
    private String urlThumbnail;

    public ItemAlbums(String nameAlbum, String artist, String urlThumbnail) {
        this.nameAlbum = nameAlbum;
        this.artist = artist;
        this.urlThumbnail = urlThumbnail;
    }

    public String getNameAlbum() {
        return nameAlbum;
    }

    public void setNameAlbum(String nameAlbum) {
        this.nameAlbum = nameAlbum;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getUrlThumbnail() {
        return urlThumbnail;
    }

    public void setUrlThumbnail(String urlThumbnail) {
        this.urlThumbnail = urlThumbnail;
    }
}
