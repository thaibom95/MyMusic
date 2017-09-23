package com.example.thaikv.musicdemo.views;

import com.example.thaikv.musicdemo.models.FolderMusicStruct;
import com.example.thaikv.musicdemo.models.SongMusicStruct;

import java.util.ArrayList;

public interface FoldersFragmentView {
    void onGetListFolderSuccess(ArrayList<FolderMusicStruct> folderMusicStructs);

    void onGetListFolderFail();
}
