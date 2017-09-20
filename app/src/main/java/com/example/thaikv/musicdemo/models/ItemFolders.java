package com.example.thaikv.musicdemo.models;

public class ItemFolders {
    private String nameFolder;
    private boolean isFile;

    public ItemFolders(String nameFolder, boolean isFile) {
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
