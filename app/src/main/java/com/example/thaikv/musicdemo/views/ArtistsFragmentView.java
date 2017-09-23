package com.example.thaikv.musicdemo.views;

import com.example.thaikv.musicdemo.models.ArtistMusicStruct;

import java.util.ArrayList;

public interface ArtistsFragmentView {
    void onGetListArtistSuccess(ArrayList<ArtistMusicStruct> artistMusicStructs);

    void onGetListArtistFail();
}
