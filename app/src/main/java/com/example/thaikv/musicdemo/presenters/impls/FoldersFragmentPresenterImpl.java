package com.example.thaikv.musicdemo.presenters.impls;

import com.example.thaikv.musicdemo.databases.SongDBManager;
import com.example.thaikv.musicdemo.listenters.OnGetListFolderResult;
import com.example.thaikv.musicdemo.models.FolderMusicStruct;
import com.example.thaikv.musicdemo.presenters.FoldersFragmentPresenter;
import com.example.thaikv.musicdemo.views.FoldersFragmentView;

import java.util.ArrayList;

public class FoldersFragmentPresenterImpl implements FoldersFragmentPresenter, OnGetListFolderResult {
    private FoldersFragmentView mFoldersFragmentView;

    @Override
    public void getListFolder() {
        SongDBManager.getInstance().getAllFolders(this);
    }

    @Override
    public void setFoldersFragmentView(FoldersFragmentView foldersFragmentView) {
        this.mFoldersFragmentView = foldersFragmentView;
    }

    @Override
    public void onGetLisFolderSuccess(ArrayList<FolderMusicStruct> folderMusicStructs) {
        mFoldersFragmentView.onGetListFolderSuccess(folderMusicStructs);
    }

    @Override
    public void onGetLisFolderFail() {
        mFoldersFragmentView.onGetListFolderFail();
    }
}
