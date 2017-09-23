package com.example.thaikv.musicdemo.fragments;

import android.annotation.SuppressLint;

import com.example.thaikv.musicdemo.models.GenresMusicStruct;
import com.example.thaikv.musicdemo.presenters.GenresFragmentPresenter;
import com.example.thaikv.musicdemo.presenters.impls.GenresFragmentPresenterImpl;
import com.example.thaikv.musicdemo.views.GenresFragmentView;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class GenresFragment extends BaseFragment<GenresMusicStruct> implements GenresFragmentView {
    private GenresFragmentPresenter genresFragmentPresenter;

    public GenresFragment(int idLayout, int idRecyclerView, int idProgressBar) {
        super(idLayout, idRecyclerView, idProgressBar);
        genresFragmentPresenter = new GenresFragmentPresenterImpl();
        genresFragmentPresenter.setGenresFragmentView(this);
    }

    @Override
    protected void initData() {
        genresFragmentPresenter.getListGenre();
    }

    @Override
    protected void onClickItemListenerBase(int position) {

    }

    @Override
    public void onGetListGenreSuccess(ArrayList<GenresMusicStruct> genresMusicStructs) {
        arrayList = genresMusicStructs;
        baseAdapter.setArrayList(arrayList);
        baseAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetListGenreFail() {

    }
}

