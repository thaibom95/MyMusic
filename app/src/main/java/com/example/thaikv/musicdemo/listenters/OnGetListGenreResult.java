package com.example.thaikv.musicdemo.listenters;

import com.example.thaikv.musicdemo.models.GenresMusicStruct;
import com.example.thaikv.musicdemo.models.SongMusicStruct;

import java.util.ArrayList;

public interface OnGetListGenreResult {
    void onGetLisGenreSuccess(ArrayList<GenresMusicStruct> genresMusicStructs);

    void onGetLisGenreFail();
}
