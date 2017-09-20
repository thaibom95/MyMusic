package com.example.thaikv.musicdemo.views;

import com.example.thaikv.musicdemo.models.ItemTracks;
import com.example.thaikv.musicdemo.models.SongMusicStruct;

import java.util.ArrayList;

public interface TracksFragmentView {
    void getListTrackSuccess(ArrayList<SongMusicStruct> tracksArrayList);

    void getListTrackFail();
}
