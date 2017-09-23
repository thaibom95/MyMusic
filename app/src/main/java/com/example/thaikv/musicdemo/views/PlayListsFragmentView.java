package com.example.thaikv.musicdemo.views;

import com.example.thaikv.musicdemo.models.PlayListsMusicStruct;

import java.util.ArrayList;

public interface PlayListsFragmentView {
    void onGetListPlayListSuccess(ArrayList<PlayListsMusicStruct> playListseMusicStructs);

    void onGetListPlayListFail();
}
