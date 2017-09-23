package com.example.thaikv.musicdemo.presenters.impls;

import com.example.thaikv.musicdemo.presenters.PlayListsFragmentPresenter;
import com.example.thaikv.musicdemo.views.PlayListsFragmentView;

public class PlayListsFragmentPresenterImpl implements PlayListsFragmentPresenter {
    private PlayListsFragmentView mPlayListsFragmentView;

    @Override
    public void getListPlayList() {

    }

    @Override
    public void setPlayListsFragmentView(PlayListsFragmentView PlayListsFragmentView) {
        this.mPlayListsFragmentView = PlayListsFragmentView;
    }

}
