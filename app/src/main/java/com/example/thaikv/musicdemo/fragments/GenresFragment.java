package com.example.thaikv.musicdemo.fragments;

import android.annotation.SuppressLint;

import com.example.thaikv.musicdemo.models.ItemGenres;
import com.example.thaikv.musicdemo.models.ItemTracks;

@SuppressLint("ValidFragment")
public class GenresFragment extends BaseFragment<ItemGenres> {

    public GenresFragment(int idLayout, int idRecyclerView, int idProgressBar) {
        super(idLayout, idRecyclerView, idProgressBar);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onClickItemListenerBase(int position) {

    }
}

