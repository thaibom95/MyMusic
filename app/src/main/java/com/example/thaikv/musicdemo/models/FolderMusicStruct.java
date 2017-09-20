package com.example.thaikv.musicdemo.models;

public class FolderMusicStruct {
    private String name;
    private int numberSong;
    private String path;

    public FolderMusicStruct() {
    }

    public FolderMusicStruct(String name, int numberSong, String path) {
        this.name = name;
        this.numberSong = numberSong;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberSong() {
        return numberSong;
    }

    public void setNumberSong(int numberSong) {
        this.numberSong = numberSong;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
