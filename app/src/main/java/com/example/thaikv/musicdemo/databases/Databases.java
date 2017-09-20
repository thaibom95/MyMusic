package com.example.thaikv.musicdemo.databases;

public class Databases {
    public static Databases INSTANCE;

    private Databases() {

    }

    public static Databases getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Databases();
        }
        return INSTANCE;
    }
}
