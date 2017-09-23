package com.example.thaikv.musicdemo.listenters;

import com.example.thaikv.musicdemo.models.ArtistMusicStruct;

import java.util.ArrayList;

public interface OnGetListArtistResult {
    void onGetLisArtistSuccess(ArrayList<ArtistMusicStruct> artistMusicStructs);

    void onGetLisArtistFail();
}
