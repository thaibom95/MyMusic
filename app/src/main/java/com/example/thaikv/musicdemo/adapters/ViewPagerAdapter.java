package com.example.thaikv.musicdemo.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.thaikv.musicdemo.R;
import com.example.thaikv.musicdemo.fragments.AlbumsFragment;
import com.example.thaikv.musicdemo.fragments.ArtistsFragment;
import com.example.thaikv.musicdemo.fragments.FoldersFragment;
import com.example.thaikv.musicdemo.fragments.GenresFragment;
import com.example.thaikv.musicdemo.fragments.PlaylistsFragment;
import com.example.thaikv.musicdemo.fragments.TracksFragment;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private String[] titleTabLayouts;
    private int tabCount;
    private ArrayList<Fragment> fragmentArrayList;

    public ViewPagerAdapter(Context context, FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
        titleTabLayouts = context.getResources().getStringArray(R.array.title_tab_layout);

        fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(new TracksFragment(R.layout.fragment_tracks, R.id.fs_rcv_tracks, R.id.pgb_loading_tracks));
        fragmentArrayList.add(new AlbumsFragment(R.layout.fragment_albums, R.id.fs_rcv_albums, R.id.pgb_loading_albums));
        fragmentArrayList.add(new ArtistsFragment(R.layout.fragment_artists, R.id.fs_rcv_artists, R.id.pgb_loading_artists));
        fragmentArrayList.add(new GenresFragment(R.layout.fragment_genres, R.id.fs_rcv_genres, R.id.pgb_loading_genres));
        fragmentArrayList.add(new PlaylistsFragment(R.layout.fragment_playlists, R.id.fs_rcv_playlists, R.id.pgb_loading_playlists));
        fragmentArrayList.add(new FoldersFragment(R.layout.fragments_folders, R.id.fs_rcv_folders, R.id.pgb_loading_folders));
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return fragmentArrayList.get(0);

            case 1:
                return fragmentArrayList.get(1);

            case 2:
                return fragmentArrayList.get(2);

            case 3:
                return fragmentArrayList.get(3);

            case 4:
                return fragmentArrayList.get(4);

            case 5:
                return fragmentArrayList.get(5);

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        super.getPageTitle(position);
        switch (position) {
            case 0:
                return titleTabLayouts[0];

            case 1:
                return titleTabLayouts[1];

            case 2:
                return titleTabLayouts[2];

            case 3:
                return titleTabLayouts[3];

            case 4:
                return titleTabLayouts[4];

            case 5:
                return titleTabLayouts[5];

            default:
                return "";
        }
    }
}
