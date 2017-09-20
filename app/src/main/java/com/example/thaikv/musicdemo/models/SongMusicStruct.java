package com.example.thaikv.musicdemo.models;

public class SongMusicStruct {
    private Long idSong;
    private Long duration;
    private String artist;
    private String name;
    private String path;
    private String album;
    private Long idAlbum;

    public SongMusicStruct() {
    }

    public SongMusicStruct(Long idSong, Long duration, String artist, String name, String path, String album, Long idAlbum) {
        this.idSong = idSong;
        this.duration = duration;
        this.artist = artist;
        this.name = name;
        this.path = path;
        this.album = album;
        this.idAlbum = idAlbum;
    }

    public Long getIdSong() {
        return idSong;
    }

    public void setIdSong(Long idSong) {
        this.idSong = idSong;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Long getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(Long idAlbum) {
        this.idAlbum = idAlbum;
    }
}
