package com.example.thaikv.musicdemo.listenters;

import com.example.thaikv.musicdemo.models.PlayListsMusicStruct;

import java.util.ArrayList;

public interface OnGetListPlayListResult {
    void onGetLisPlayListSuccess(ArrayList<PlayListsMusicStruct> playListseMusicStructs);

    void onGetLisPlayListFail();
}
