package com.example.thaikv.musicdemo.fragments;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.thaikv.musicdemo.models.ItemTracks;
import com.example.thaikv.musicdemo.models.SongMusicStruct;
import com.example.thaikv.musicdemo.presenters.TracksFragmentPresenter;
import com.example.thaikv.musicdemo.presenters.impls.TracksFragmentPresenterImpl;
import com.example.thaikv.musicdemo.views.TracksFragmentView;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class TracksFragment extends BaseFragment<SongMusicStruct> implements TracksFragmentView {
    private static final String TAG = "TracksFragment";
    private TracksFragmentPresenter tracksFragmentPresenter;

    public TracksFragment(int idLayout, int idRecyclerView, int idProgressBar) {
        super(idLayout, idRecyclerView, idProgressBar);
        tracksFragmentPresenter = new TracksFragmentPresenterImpl();
        tracksFragmentPresenter.setTracksFragmentView(this);
    }

    @Override
    protected void initData() {
        tracksFragmentPresenter.getListTrack();
    }

    @Override
    protected void onClickItemListenerBase(int position) {

    }

    @Override
    public void getListTrackSuccess(ArrayList<SongMusicStruct> tracksArrayList) {
        arrayList = tracksArrayList;
        baseAdapter.setArrayList(arrayList);
        baseAdapter.notifyDataSetChanged();
    }

    @Override
    public void getListTrackFail() {

    }
}

