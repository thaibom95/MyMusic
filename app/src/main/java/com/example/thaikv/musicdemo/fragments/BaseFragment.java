package com.example.thaikv.musicdemo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thaikv.musicdemo.R;
import com.example.thaikv.musicdemo.adapters.AlbumsAdapter;
import com.example.thaikv.musicdemo.adapters.ArtistsAdapter;
import com.example.thaikv.musicdemo.adapters.BaseAdapter;
import com.example.thaikv.musicdemo.adapters.FoldersAdapter;
import com.example.thaikv.musicdemo.adapters.GenresAdapter;
import com.example.thaikv.musicdemo.adapters.PlayListsAdapter;
import com.example.thaikv.musicdemo.adapters.TracksAdapter;
import com.simplecityapps.recyclerview_fastscroll.views.FastScrollRecyclerView;

import java.util.ArrayList;

public abstract class BaseFragment<T> extends Fragment implements BaseAdapter.OnClickItemListener {
    private View rootView;
    private FastScrollRecyclerView fastScrollRecyclerView;
    protected BaseAdapter baseAdapter;
    protected ArrayList<T> arrayList;


    private int idLayout;
    private int idRecyclerView;
    private int idProgressBar;

    public BaseFragment(int idLayout, int idRecyclerView, int idProgressBar) {
        this.idLayout = idLayout;
        this.idRecyclerView = idRecyclerView;
        this.idProgressBar = idProgressBar;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initViews(inflater, container);
        initEvents();
        initData();
        return rootView;
    }

    protected void initViews(LayoutInflater inflater, ViewGroup container) {
        rootView = inflater.inflate(idLayout, container, false);

        fastScrollRecyclerView = (FastScrollRecyclerView) rootView.findViewById(idRecyclerView);
        fastScrollRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        fastScrollRecyclerView.setItemAnimator(new DefaultItemAnimator());
        switch (idLayout) {
            case R.layout.fragment_tracks:
                baseAdapter = new TracksAdapter(getActivity(), fastScrollRecyclerView);
                break;

            case R.layout.fragment_albums:
                baseAdapter = new AlbumsAdapter(getActivity(), fastScrollRecyclerView);
                break;

            case R.layout.fragment_artists:
                baseAdapter = new ArtistsAdapter(getActivity(), fastScrollRecyclerView);
                break;

            case R.layout.fragment_genres:
                baseAdapter = new GenresAdapter(getActivity(), fastScrollRecyclerView);
                break;

            case R.layout.fragment_playlists:
                baseAdapter = new PlayListsAdapter(getActivity(), fastScrollRecyclerView);
                break;

            case R.layout.fragments_folders:
                baseAdapter = new FoldersAdapter(getActivity(), fastScrollRecyclerView);
                break;
        }

        fastScrollRecyclerView.setAdapter(baseAdapter);
    }

    protected void initEvents() {
        baseAdapter.setOnClickItemListener(this);
    }

    protected abstract void initData();

    protected abstract void onClickItemListenerBase(int position);

    @Override
    public void onClickItemListener(int position) {
        onClickItemListenerBase(position);
    }
}
