package com.example.thaikv.musicdemo.fragments;

import android.annotation.SuppressLint;

import android.content.Intent;

import com.example.thaikv.musicdemo.activitys.PlayerActivity;
import com.example.thaikv.musicdemo.controllers.MusicPlayer;
import com.example.thaikv.musicdemo.models.SongMusicStruct;
import com.example.thaikv.musicdemo.presenters.TracksFragmentPresenter;
import com.example.thaikv.musicdemo.presenters.impls.TracksFragmentPresenterImpl;
import com.example.thaikv.musicdemo.services.PlayTrackService;
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



        if(MusicPlayer.getTyleListSongs() != PlayTrackService.PLAYLIST){
            if(MusicPlayer.setPlaylist(arrayList)){
                MusicPlayer.setTyleListSongs(PlayTrackService.PLAYLIST);
                MusicPlayer.playWithPos(position);
            }
        }else {
            MusicPlayer.playWithPos(position);
        }
        startActivity(new Intent(getActivity(), PlayerActivity.class));
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

