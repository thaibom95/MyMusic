package com.example.thaikv.musicdemo.controllers;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.thaikv.musicdemo.models.SongMusicStruct;
import com.example.thaikv.musicdemo.services.PlayTrackService;

import java.util.ArrayList;
import java.util.WeakHashMap;

/**
 * Created by KienPC on 09/20/17.
 */

public class MusicPlayer {


    private static final WeakHashMap<Context, ServiceBinder> mConnectionMap;
    public static PlayTrackService mService = null;


    static {
        mConnectionMap = new WeakHashMap<Context, ServiceBinder>();
//        sEmptyList = new long[0];
    }

    public static final ServiceToken bindToService(final Context context,
                                                   final ServiceConnection callback) {

        Activity realActivity = ((Activity) context).getParent();
        if (realActivity == null) {
            realActivity = (Activity) context;
        }
        final ContextWrapper contextWrapper = new ContextWrapper(realActivity);
        contextWrapper.startService(new Intent(contextWrapper, PlayTrackService.class));
        final ServiceBinder binder = new ServiceBinder(callback,
                contextWrapper.getApplicationContext());
        if (contextWrapper.bindService(
                new Intent().setClass(contextWrapper, PlayTrackService.class), binder, 0)) {
            mConnectionMap.put(contextWrapper, binder);
            return new ServiceToken(contextWrapper);
        }
        return null;
    }

    public static void unbindFromService(final ServiceToken token) {
        if (token == null) {
            return;
        }
        final ContextWrapper mContextWrapper = token.mWrappedContext;
        final ServiceBinder mBinder = mConnectionMap.remove(mContextWrapper);
        if (mBinder == null) {
            return;
        }
        mContextWrapper.unbindService(mBinder);
        if (mConnectionMap.isEmpty()) {
            mService = null;
        }
    }

    public static SongMusicStruct getCurrentSongPlay(){
        if (mService != null) {
            return mService.getCurrenSongPlay();
        }
        return null;

    }
    public static void next() {
        if (mService != null) {
            mService.nextSongService();
        }
    }

    public static void prev() {
        if (mService != null) {
            mService.preveSongService();
        }
    }

    public static boolean setPlaylist(ArrayList<SongMusicStruct> lisSong){
        if(mService != null){
            mService.setListSongPlay(lisSong);
            return true;
        }
        return false;
    }

    public static void playWithPos(int pos) {
        if (mService != null) {
            mService.playSongWithPos(pos);
        }
    }

    public static int getTyleListSongs(){
        if(mService != null){
            return mService.getListSongType();
        }
        return -1;
    }

    public static void setTyleListSongs(int type){
        if(mService != null){
            mService.setListSongType(type);
        }
    }
    public static boolean isSongPlaying(){
        if(mService != null){
            return mService.isPlayingSong();
        }
        return false;
    }

    public static long getCurrentPositionPlay(){
        if(mService != null){
           return mService.getCurrentTimePlay();
        }
        return 0;
    }
    public static void seekSongPlayto(int s){
        if(mService != null){
          mService.seekSongPlayTo(s);
        }

    }


    public static void previous(final Context context, final boolean force) {
        final Intent previous = new Intent(context, PlayTrackService.class);
        if (force) {
            previous.setAction(PlayTrackService.PREVIOUS_FORCE_ACTION);
        } else {
            previous.setAction(PlayTrackService.PREVIOUS_ACTION);
        }
        context.startService(previous);
    }


    public static void playOrPause() {
        if (mService != null) {
            if (mService.isPlayingSong()) {
                mService.pauseSongService();
            } else {
                mService.playBackSongService();
            }
        }

    }

    public static void cycleRepeat() {
        if (mService != null) {
            switch (mService.getRepeatMode()) {
                case PlayTrackService.REPEAT_NONE:
                    mService.setRepeatMode(PlayTrackService.REPEAT_ALL);
                    break;
                case PlayTrackService.REPEAT_ALL:
                    mService.setRepeatMode(PlayTrackService.REPEAT_CURRENT);
                    if (mService.getShuffleMode() != PlayTrackService.SHUFFLE_NONE) {
                        mService.setShuffleMode(PlayTrackService.SHUFFLE_NONE);
                    }
                    break;
                default:
                    mService.setRepeatMode(PlayTrackService.REPEAT_NONE);
                    break;
            }
        }

    }


    public static final class ServiceBinder implements ServiceConnection {
        private final ServiceConnection mCallback;
        private final Context mContext;


        public ServiceBinder(final ServiceConnection callback, final Context context) {
            mCallback = callback;
            mContext = context;
        }

        @Override
        public void onServiceConnected(final ComponentName className, final IBinder service) {

            PlayTrackService.MusicBinder binder = (PlayTrackService.MusicBinder) service;
            mService = binder.getService();
            if (mCallback != null) {
                mCallback.onServiceConnected(className, service);
            }
        }

        @Override
        public void onServiceDisconnected(final ComponentName className) {
            if (mCallback != null) {
                mCallback.onServiceDisconnected(className);
            }
            mService = null;
        }
    }

    public static final class ServiceToken {
        public ContextWrapper mWrappedContext;

        public ServiceToken(final ContextWrapper context) {
            mWrappedContext = context;
        }
    }
}
