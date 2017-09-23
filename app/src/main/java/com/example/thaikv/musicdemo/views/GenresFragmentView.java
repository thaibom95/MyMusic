package com.example.thaikv.musicdemo.views;

import com.example.thaikv.musicdemo.models.GenresMusicStruct;
import com.example.thaikv.musicdemo.models.SongMusicStruct;

import java.util.ArrayList;

public interface GenresFragmentView {
    void onGetListGenreSuccess(ArrayList<GenresMusicStruct> genresMusicStructs);

    void onGetListGenreFail();
}
