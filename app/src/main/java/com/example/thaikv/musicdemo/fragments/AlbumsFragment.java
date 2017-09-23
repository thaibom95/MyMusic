package com.example.thaikv.musicdemo.fragments;

import android.annotation.SuppressLint;

import com.example.thaikv.musicdemo.models.AlbumMusicStruct;
import com.example.thaikv.musicdemo.presenters.AlbumsFragmentPresenter;
import com.example.thaikv.musicdemo.presenters.impls.AlbumsFragmentPresenterImpl;
import com.example.thaikv.musicdemo.views.AlbumsFragmentView;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class AlbumsFragment extends BaseFragment<AlbumMusicStruct> implements AlbumsFragmentView {
    private static final String TAG = "AlbumsFragment";
    private AlbumsFragmentPresenter albumsFragmentPresenter;

    public AlbumsFragment(int idLayout, int idRecyclerView, int idProgressBar) {
        super(idLayout, idRecyclerView, idProgressBar);
        albumsFragmentPresenter = new AlbumsFragmentPresenterImpl();
        albumsFragmentPresenter.setAlbumsFragmentView(this);
    }

    @Override
    protected void initData() {
        albumsFragmentPresenter.getListAlbum();
    }

    @Override
    protected void onClickItemListenerBase(int position) {

    }

    @Override
    public void onGetListAlbumSuccess(ArrayList<AlbumMusicStruct> albumMusicStructs) {
        arrayList = albumMusicStructs;
        baseAdapter.setArrayList(arrayList);
        baseAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetListAlbumFail() {

    }
}

