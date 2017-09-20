package com.example.thaikv.musicdemo.fragments;

import android.annotation.SuppressLint;

import com.example.thaikv.musicdemo.models.ItemPlayLists;

@SuppressLint("ValidFragment")
public class PlaylistsFragment extends BaseFragment<ItemPlayLists> {

    public PlaylistsFragment(int idLayout, int idRecyclerView, int idProgressBar) {
        super(idLayout, idRecyclerView, idProgressBar);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onClickItemListenerBase(int position) {

    }
}

