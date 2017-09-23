package com.example.thaikv.musicdemo.listenters;

import com.example.thaikv.musicdemo.models.FolderMusicStruct;
import com.example.thaikv.musicdemo.models.SongMusicStruct;

import java.util.ArrayList;

public interface OnGetListFolderResult {
    void onGetLisFolderSuccess(ArrayList<FolderMusicStruct> folderMusicStructs);

    void onGetLisFolderFail();
}
