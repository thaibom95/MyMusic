package com.example.thaikv.musicdemo.presenters.impls;

import com.example.thaikv.musicdemo.databases.SongDBManager;
import com.example.thaikv.musicdemo.listenters.OnGetListArtistResult;
import com.example.thaikv.musicdemo.models.ArtistMusicStruct;
import com.example.thaikv.musicdemo.presenters.ArtistsFragmentPresenter;
import com.example.thaikv.musicdemo.views.ArtistsFragmentView;

import java.util.ArrayList;

public class ArtistsFragmentPresenterImpl implements ArtistsFragmentPresenter, OnGetListArtistResult {
    private ArtistsFragmentView mArtistsFragmentView;

    @Override
    public void getListArtist() {
        SongDBManager.getInstance().getListArtist(this);
    }

    @Override
    public void setArtistsFragmentView(ArtistsFragmentView artistsFragmentView) {
        this.mArtistsFragmentView = artistsFragmentView;
    }

    @Override
    public void onGetLisArtistSuccess(ArrayList<ArtistMusicStruct> artistMusicStructs) {
        mArtistsFragmentView.onGetListArtistSuccess(artistMusicStructs);
    }

    @Override
    public void onGetLisArtistFail() {
        mArtistsFragmentView.onGetListArtistFail();
    }
}
