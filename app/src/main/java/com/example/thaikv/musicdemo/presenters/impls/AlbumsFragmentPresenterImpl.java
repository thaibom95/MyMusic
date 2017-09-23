package com.example.thaikv.musicdemo.presenters.impls;

import com.example.thaikv.musicdemo.databases.SongDBManager;
import com.example.thaikv.musicdemo.listenters.OnGetListAlbumResult;
import com.example.thaikv.musicdemo.models.AlbumMusicStruct;
import com.example.thaikv.musicdemo.presenters.AlbumsFragmentPresenter;
import com.example.thaikv.musicdemo.views.AlbumsFragmentView;

import java.util.ArrayList;

public class AlbumsFragmentPresenterImpl implements AlbumsFragmentPresenter, OnGetListAlbumResult {
    private AlbumsFragmentView mAlbumsFragmentView;

    @Override
    public void getListAlbum() {
        SongDBManager.getInstance().getAlbumSongs(this);
    }

    @Override
    public void setAlbumsFragmentView(AlbumsFragmentView AlbumsFragmentView) {
        this.mAlbumsFragmentView = AlbumsFragmentView;
    }

    @Override
    public void onGetLisAlbumSuccess(ArrayList<AlbumMusicStruct> albumMusicStructs) {
        mAlbumsFragmentView.onGetListAlbumSuccess(albumMusicStructs);
    }

    @Override
    public void onGetLisAlbumFail() {
        mAlbumsFragmentView.onGetListAlbumFail();
    }
}
