package com.example.thaikv.musicdemo.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.ContentUris;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v7.app.NotificationCompat;
import android.support.v7.graphics.Palette;
import android.text.TextUtils;
import android.util.Log;

import com.example.thaikv.musicdemo.R;
import com.example.thaikv.musicdemo.models.SongMusicStruct;
import com.example.thaikv.musicdemo.utils.NavigationUtils;
import com.example.thaikv.musicdemo.utils.Utils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

public class PlayTrackService extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener {

    public static final String PREFIX = "com.example.thaikv.musicdemo.";

    public static final String PREVIOUS_ACTION = PREFIX + "previous";
    public static final String PREVIOUS_FORCE_ACTION = PREFIX + "previous.force";

    public static final String NEXT_ACTION = PREFIX + "next";
    public static final String TOGGLEPAUSE_ACTION = PREFIX + "togglepause";


    //repeat mode
    public static final int REPEAT_NONE = 0;
    public static final int REPEAT_CURRENT = 1;
    public static final int REPEAT_ALL = 2;
    //shuffle mode
    public static final int SHUFFLE_NONE = 0;
    public static final int SHUFFLE_NORMAL = 1;
    public static final int SHUFFLE_AUTO = 2;

    //motification mode
    private static final int NOTIFY_MODE_NONE = 0;
    private static final int NOTIFY_MODE_FOREGROUND = 1;
    private static final int NOTIFY_MODE_BACKGROUND = 2;
    private NotificationManagerCompat mNotificationManager;

    private int mNotifyMode = NOTIFY_MODE_NONE;

    //list songs type
    public static final int PLAYLIST = 0;
    public static final int ALLSONG = 1;
    public static final int ALBUMS = 2;

    private MediaPlayer mediaPlayer;
    private final IBinder musicBind = new MusicBinder();
    private long mNotificationPostTime = 0;

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
        mNotificationManager = NotificationManagerCompat.from(this);
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
//        mediaPlayer.stop();
//        mediaPlayer.release();
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
        updateNotification();

    }


    public class MusicBinder extends Binder {
        public PlayTrackService getService() {
            return PlayTrackService.this;
        }
    }


    private Notification buildNotification() {
        final String albumName = songPlay.getAlbum();
        final String artistName = songPlay.getArtist();
        final boolean isPlaying = isPlayingSong();
        String text = TextUtils.isEmpty(albumName)
                ? artistName : artistName + " - " + albumName;

        int playButtonResId = isPlaying
                ? R.drawable.ic_pause_white_36dp : R.drawable.ic_play_white_36dp;

        Intent nowPlayingIntent = NavigationUtils.getHomeIntent(this);
        PendingIntent clickIntent = PendingIntent.getActivity(this, 0, nowPlayingIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        final Bitmap[] artwork = new Bitmap[1];
        long albumId = songPlay.getIdAlbum();
        if (albumId != -1) {
            Uri uri_image = Utils.getAlbumArtUri(albumId);
            Picasso.with(getApplicationContext()).load(uri_image).into(new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    artwork[0] = bitmap;
                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {

                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                }
            });

        }

        if (artwork[0] == null) {
            Utils.drawableToBitmap(getResources().getDrawable(R.drawable.ic_empty_music2));
        }

        if (mNotificationPostTime == 0) {
            mNotificationPostTime = System.currentTimeMillis();
        }

        android.support.v4.app.NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_notification)
                .setLargeIcon(artwork[0])
                .setContentIntent(clickIntent)
                .setContentTitle(songPlay.getName())
                .setContentText(text)
                .setWhen(mNotificationPostTime)
                .addAction(R.drawable.ic_skip_previous_white_36dp,
                        "",
                        retrievePlaybackAction(PREVIOUS_ACTION))
                .addAction(playButtonResId, "",
                        retrievePlaybackAction(TOGGLEPAUSE_ACTION))
                .addAction(R.drawable.ic_skip_next_white_36dp,
                        "",
                        retrievePlaybackAction(NEXT_ACTION));

        if (Utils.isJellyBeanMR1()) {
            builder.setShowWhen(false);
        }
        if (Utils.isLollipop()) {
            builder.setVisibility(Notification.VISIBILITY_PUBLIC);
            NotificationCompat.MediaStyle style = new NotificationCompat.MediaStyle()
                    .setMediaSession(null)
                    .setShowActionsInCompactView(0, 1, 2, 3);
            builder.setStyle(style);
        }
        if (artwork[0] != null && Utils.isLollipop())
            builder.setColor(Palette.from(artwork[0]).generate().getVibrantColor(Color.parseColor("#403f4d")));
        Notification n = builder.build();

        return n;
    }

    private final PendingIntent retrievePlaybackAction(final String action) {
        final ComponentName serviceName = new ComponentName(this, PlayTrackService.class);
        Intent intent = new Intent(action);
        intent.setComponent(serviceName);

        return PendingIntent.getService(this, 0, intent, 0);
    }


    private void updateNotification() {
        final int newNotifyMode;
        if (isPlayingSong()) {
            newNotifyMode = NOTIFY_MODE_FOREGROUND;
        }
//        else if (recentlyPlayed()) {
//            newNotifyMode = NOTIFY_MODE_BACKGROUND;
//        }
        else {
            newNotifyMode = NOTIFY_MODE_NONE;
        }

        int notificationId = hashCode();
        if (mNotifyMode != newNotifyMode) {
            if (mNotifyMode == NOTIFY_MODE_FOREGROUND) {
                if (Utils.isLollipop())
                    stopForeground(newNotifyMode == NOTIFY_MODE_NONE);
                else
                    stopForeground(newNotifyMode == NOTIFY_MODE_NONE || newNotifyMode == NOTIFY_MODE_BACKGROUND);
            } else if (newNotifyMode == NOTIFY_MODE_NONE) {
                mNotificationManager.cancel(notificationId);
                mNotificationPostTime = 0;
            }
        }

        if (newNotifyMode == NOTIFY_MODE_FOREGROUND) {
            startForeground(notificationId, buildNotification());
        } else if (newNotifyMode == NOTIFY_MODE_BACKGROUND) {
            mNotificationManager.notify(notificationId, buildNotification());
        }

        mNotifyMode = newNotifyMode;
    }

    private void cancelNotification() {
        stopForeground(true);
        mNotificationManager.cancel(hashCode());
        mNotificationPostTime = 0;
        mNotifyMode = NOTIFY_MODE_NONE;
    }

}
