package com.example.thaikv.musicdemo.views;

import com.example.thaikv.musicdemo.models.AlbumMusicStruct;

import java.util.ArrayList;

public interface AlbumsFragmentView {
    void onGetListAlbumSuccess(ArrayList<AlbumMusicStruct> albumMusicStructs);

    void onGetListAlbumFail();
}
