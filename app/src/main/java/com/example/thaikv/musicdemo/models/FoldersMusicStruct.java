package com.example.thaikv.musicdemo.models;

public class FoldersMusicStruct {
    private String nameFolder;
    private boolean isFile;

    public FoldersMusicStruct(String nameFolder, boolean isFile) {
        this.nameFolder = nameFolder;
        this.isFile = isFile;
    }

    public String getNameFolder() {
        return nameFolder;
    }

    public void setNameFolder(String nameFolder) {
        this.nameFolder = nameFolder;
    }

    public boolean isFile() {
        return isFile;
    }

    public void setFile(boolean file) {
        isFile = file;
    }
}
