package com.example.thaikv.musicdemo.services;

import android.app.Service;
import android.content.ContentUris;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;

import com.example.thaikv.musicdemo.models.SongMusicStruct;

import java.util.ArrayList;

public class PlayTrackService extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener {


    public static final String PREVIOUS_ACTION = "com.example.thaikv.musicdemo.previous";
    public static final String PREVIOUS_FORCE_ACTION = "com.example.thaikv.musicdemo.previous.force";


    //repeat mode
    public static final int REPEAT_NONE = 0;
    public static final int REPEAT_CURRENT = 1;
    public static final int REPEAT_ALL = 2;
    //shuffle mode
    public static final int SHUFFLE_NONE = 0;
    public static final int SHUFFLE_NORMAL = 1;
    public static final int SHUFFLE_AUTO = 2;

    //list songs type
    public static final int PLAYLIST = 0;
    public static final int ALLSONG = 1;
    public static final int ALBUMS = 2;

    private MediaPlayer mediaPlayer;
    private final IBinder musicBind = new MusicBinder();

    private ArrayList<SongMusicStruct> listSongPlay;
    private SongMusicStruct songPlay;
    private int indexSong;
    private int repeatMode = 0;
    private int shuffleMode = 0;
    private int typeCurrentListSongs = -1;


    public PlayTrackService() {
    }

    public ArrayList<SongMusicStruct> getListSongPlay() {
        return listSongPlay;
    }

    public void setListSongPlay(ArrayList<SongMusicStruct> listSongPlay) {
        this.listSongPlay = listSongPlay;

    }

    public void initPlayer() {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            final AudioAttributes audioAttributes =
                    new AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .build();

            mediaPlayer.setAudioAttributes(audioAttributes);
        } else
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnErrorListener(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        indexSong = 0;
        initPlayer();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return musicBind;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        mediaPlayer.stop();
        mediaPlayer.release();
        return super.onUnbind(intent);
    }

    public void playSongService() {

        mediaPlayer.reset();

        Uri trackUri = ContentUris.withAppendedId(
                android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                songPlay.getIdSong());
        try {
            mediaPlayer.setDataSource(getApplicationContext(), trackUri);
        } catch (Exception e) {
            Log.e("MUSIC SERVICE", "Error setting data source", e);
        }
        mediaPlayer.prepareAsync();
    }

    public void pauseSongService() {
        mediaPlayer.pause();
    }

    public boolean isPlayingSong() {
        return mediaPlayer.isPlaying();
    }

    public void playSongWithPos(int pos) {
        indexSong = pos;
        songPlay = listSongPlay.get(pos);
        playSongService();

    }

    public void nextSongService() {

    }

    public int getRepeatMode() {
        return repeatMode;
    }

    public void setRepeatMode(int mode) {
        this.repeatMode = mode;
    }

    public int getShuffleMode() {
        return shuffleMode;
    }

    public void setShuffleMode(int mode) {
        this.shuffleMode = mode;
    }

    public int getListSongType() {
        return typeCurrentListSongs;
    }

    public void setListSongType(int type) {
        this.typeCurrentListSongs = type;
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {

    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {

        mediaPlayer.start();

    }


    public class MusicBinder extends Binder {
        public PlayTrackService getService() {
            return PlayTrackService.this;
        }
    }
}
