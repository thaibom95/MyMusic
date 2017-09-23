package com.example.thaikv.musicdemo.listenters;

import com.example.thaikv.musicdemo.models.SongMusicStruct;

import java.util.ArrayList;

public interface OnGetListTrackResult {
    void onGetLisTrackSuccess(ArrayList<SongMusicStruct> songMusicStructs);

    void onGetLisTrackFail();
}
