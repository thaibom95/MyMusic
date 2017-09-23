package com.example.thaikv.musicdemo.presenters.impls;

import android.content.Context;

import com.example.thaikv.musicdemo.databases.SongDBManager;
import com.example.thaikv.musicdemo.listenters.OnGetListTrackResult;
import com.example.thaikv.musicdemo.models.SongMusicStruct;
import com.example.thaikv.musicdemo.presenters.OnGetDataResult;
import com.example.thaikv.musicdemo.presenters.TracksFragmentPresenter;
import com.example.thaikv.musicdemo.views.TracksFragmentView;

import java.util.ArrayList;

public class TracksFragmentPresenterImpl implements TracksFragmentPresenter, OnGetListTrackResult {
    private TracksFragmentView mTracksFragmentView;

    @Override
    public void getListTrack() {
        SongDBManager.getInstance().getAllSong(this);
    }

    @Override
    public void setTracksFragmentView(TracksFragmentView tracksFragmentView) {
        this.mTracksFragmentView = tracksFragmentView;
    }

    @Override
    public void onGetLisTrackSuccess(ArrayList<SongMusicStruct> songMusicStructs) {
        mTracksFragmentView.getListTrackSuccess(songMusicStructs);
    }

    @Override
    public void onGetLisTrackFail() {
        mTracksFragmentView.getListTrackFail();
    }
}
