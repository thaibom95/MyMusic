package com.example.thaikv.musicdemo.listenters;

import com.example.thaikv.musicdemo.models.AlbumMusicStruct;

import java.util.ArrayList;

public interface OnGetListAlbumResult {
    void onGetLisAlbumSuccess(ArrayList<AlbumMusicStruct> albumMusicStructs);

    void onGetLisAlbumFail();
}
