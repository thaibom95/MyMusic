package com.example.thaikv.musicdemo.fragments;

import android.annotation.SuppressLint;

import com.example.thaikv.musicdemo.models.ArtistMusicStruct;
import com.example.thaikv.musicdemo.presenters.ArtistsFragmentPresenter;
import com.example.thaikv.musicdemo.presenters.impls.ArtistsFragmentPresenterImpl;
import com.example.thaikv.musicdemo.views.ArtistsFragmentView;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class ArtistsFragment extends BaseFragment<ArtistMusicStruct> implements ArtistsFragmentView {
    private ArtistsFragmentPresenter artistsFragmentPresenter;

    public ArtistsFragment(int idLayout, int idRecyclerView, int idProgressBar) {
        super(idLayout, idRecyclerView, idProgressBar);
        artistsFragmentPresenter = new ArtistsFragmentPresenterImpl();
        artistsFragmentPresenter.setArtistsFragmentView(this);
    }

    @Override
    protected void initData() {
        artistsFragmentPresenter.getListArtist();
    }

    @Override
    protected void onClickItemListenerBase(int position) {

    }

    @Override
    public void onGetListArtistSuccess(ArrayList<ArtistMusicStruct> artistMusicStructs) {
        arrayList = artistMusicStructs;
        baseAdapter.setArrayList(arrayList);
        baseAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetListArtistFail() {

    }
}

