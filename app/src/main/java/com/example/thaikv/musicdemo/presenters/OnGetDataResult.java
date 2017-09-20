package com.example.thaikv.musicdemo.presenters;

import com.example.thaikv.musicdemo.models.SongMusicStruct;

import java.util.ArrayList;

public interface OnGetDataResult {
    void onGetDataAllSongSuccess(ArrayList<SongMusicStruct> arrayList);

    void onGetDataAllSongFail();
}
