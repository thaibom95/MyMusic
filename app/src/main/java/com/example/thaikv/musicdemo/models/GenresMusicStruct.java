package com.example.thaikv.musicdemo.models;


public class GenresMusicStruct {
    private long id;
    private String name;
    private String uri;
    private int numberSongInGenres;

    public GenresMusicStruct() {
    }

    public GenresMusicStruct(long id, String name, String uri, int numberSongInGenres) {
        this.id = id;
        this.name = name;
        this.uri = uri;
        this.numberSongInGenres = numberSongInGenres;
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

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getNumberSongInGenres() {
        return numberSongInGenres;
    }

    public void setNumberSongInGenres(int numberSongInGenres) {
        this.numberSongInGenres = numberSongInGenres;
    }
}
