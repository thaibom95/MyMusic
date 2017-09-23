package com.example.thaikv.musicdemo.presenters.impls;

import com.example.thaikv.musicdemo.databases.SongDBManager;
import com.example.thaikv.musicdemo.listenters.OnGetListGenreResult;
import com.example.thaikv.musicdemo.models.GenresMusicStruct;
import com.example.thaikv.musicdemo.presenters.GenresFragmentPresenter;
import com.example.thaikv.musicdemo.views.GenresFragmentView;

import java.util.ArrayList;

public class GenresFragmentPresenterImpl implements GenresFragmentPresenter, OnGetListGenreResult {
    private GenresFragmentView mGenresFragmentView;

    @Override
    public void getListGenre() {
        SongDBManager.getInstance().getGenres(this);
    }

    @Override
    public void setGenresFragmentView(GenresFragmentView GenresFragmentView) {
        this.mGenresFragmentView = GenresFragmentView;
    }

    @Override
    public void onGetLisGenreSuccess(ArrayList<GenresMusicStruct> genresMusicStructs) {
        mGenresFragmentView.onGetListGenreSuccess(genresMusicStructs);
    }

    @Override
    public void onGetLisGenreFail() {
        mGenresFragmentView.onGetListGenreFail();
    }
}
