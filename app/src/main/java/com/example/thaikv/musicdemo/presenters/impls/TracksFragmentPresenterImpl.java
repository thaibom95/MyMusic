package com.example.thaikv.musicdemo.presenters.impls;

import android.content.Context;

import com.example.thaikv.musicdemo.databases.SongDBManager;
import com.example.thaikv.musicdemo.models.SongMusicStruct;
import com.example.thaikv.musicdemo.presenters.OnGetDataResult;
import com.example.thaikv.musicdemo.presenters.TracksFragmentPresenter;
import com.example.thaikv.musicdemo.views.TracksFragmentView;

import java.util.ArrayList;

public class TracksFragmentPresenterImpl implements TracksFragmentPresenter, OnGetDataResult {
    private TracksFragmentView mTracksFragmentView;

    public TracksFragmentPresenterImpl() {
        SongDBManager.getInstance().setOnGetDataResult(this);
    }

    @Override
    public void getListTrack() {
        SongDBManager.getInstance().getAllSong();
    }

    @Override
    public void setTracksFragmentView(TracksFragmentView tracksFragmentView) {
        this.mTracksFragmentView = tracksFragmentView;
    }

    @Override
    public void onGetDataAllSongSuccess(ArrayList<SongMusicStruct> arrayList) {
        mTracksFragmentView.getListTrackSuccess(arrayList);
    }

    @Override
    public void onGetDataAllSongFail() {
        mTracksFragmentView.getListTrackFail();
    }
}
