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
import com.example.thaikv.musicdemo.adapters.BaseAdapter;
import com.example.thaikv.musicdemo.adapters.TracksAdapter;

import java.util.ArrayList;

public abstract class BaseFragment<T> extends Fragment implements BaseAdapter.OnClickItemListener {
    private View rootView;
    private RecyclerView recyclerView;
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

        recyclerView = (RecyclerView) rootView.findViewById(idRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        switch (idLayout) {
            case R.layout.fragment_tracks:
                baseAdapter = new TracksAdapter(getActivity(), recyclerView);
                break;

            case R.layout.fragment_albums:
                baseAdapter = new TracksAdapter(getActivity(), recyclerView);
                break;

            case R.layout.fragment_artists:
                baseAdapter = new TracksAdapter(getActivity(), recyclerView);
                break;

            case R.layout.fragment_genres:
                baseAdapter = new TracksAdapter(getActivity(), recyclerView);
                break;

            case R.layout.fragment_playlists:
                baseAdapter = new TracksAdapter(getActivity(), recyclerView);
                break;

            case R.layout.fragments_folders:
                baseAdapter = new TracksAdapter(getActivity(), recyclerView);
                break;
        }

        recyclerView.setAdapter(baseAdapter);
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
