package com.example.thaikv.musicdemo.fragments;

import android.annotation.SuppressLint;

import com.example.thaikv.musicdemo.models.ItemArtists;
import com.example.thaikv.musicdemo.models.ItemTracks;

@SuppressLint("ValidFragment")
public class ArtistsFragment extends BaseFragment<ItemArtists> {

    public ArtistsFragment(int idLayout, int idRecyclerView, int idProgressBar) {
        super(idLayout, idRecyclerView, idProgressBar);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onClickItemListenerBase(int position) {

    }
}

