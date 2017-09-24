package com.example.thaikv.musicdemo.activitys;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.AsyncTask;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.thaikv.musicdemo.R;
import com.example.thaikv.musicdemo.controllers.MusicPlayer;
import com.example.thaikv.musicdemo.models.SongMusicStruct;
import com.example.thaikv.musicdemo.services.PlayTrackService;
import com.example.thaikv.musicdemo.utils.NavigationUtils;
import com.example.thaikv.musicdemo.utils.Utils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;


public class PlayerActivity extends BaseActivity implements View.OnClickListener {
    private ImageView ivBack;
    private ImageView ivShuffle;
    private ImageView ivPrevious;
    private ImageView ivPlayPause;
    private ImageView ivNext;
    private ImageView ivRepeat;
    private TextView tvAlbum;
    private TextView tvSong;
    private TextView tvArtist;
    private TextView tvCurrentTime;
    private TextView tvTotalTime;
    private SeekBar sbProgress;
    private ImageView img_album;
    private ImageView mBlurredArt;

    SongMusicStruct currentTrack;
    int overflowcounter = 0;

    private BroadcastReceiver receivSong = new ReceiveStartSong();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        initViews();
        initDataSong();
        initEvents();
        IntentFilter filter = new IntentFilter();
        filter.addAction(PlayTrackService.TOGGLEPAUSE_ACTION);
        filter.addAction(PlayTrackService.START_PLAY_NEW_SONG);
        filter.addAction(PlayTrackService.NEXT_ACTION);
        filter.addAction(PlayTrackService.PREVIOUS_ACTION);
        registerReceiver(receivSong, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receivSong);
    }

    private void initViews() {

        mBlurredArt = (ImageView) findViewById(R.id.mBlurredArt);
        img_album = (ImageView) findViewById(R.id.img_album);

        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivShuffle = (ImageView) findViewById(R.id.iv_shuffle);
        ivPrevious = (ImageView) findViewById(R.id.iv_previous);
        ivPlayPause = (ImageView) findViewById(R.id.iv_play_pause);
        ivNext = (ImageView) findViewById(R.id.iv_next);
        ivRepeat = (ImageView) findViewById(R.id.iv_repeat);

        tvAlbum = (TextView) findViewById(R.id.tv_name_album);
        tvSong = (TextView) findViewById(R.id.tv_song);
        tvArtist = (TextView) findViewById(R.id.tv_artist);
        tvCurrentTime = (TextView) findViewById(R.id.tv_current_time);
        tvTotalTime = (TextView) findViewById(R.id.tv_total_time);

        sbProgress = (SeekBar) findViewById(R.id.skb_progress);
        //setDefaultImage();

    }

    private void initEvents() {
        ivBack.setOnClickListener(this);
        ivShuffle.setOnClickListener(this);
        ivPrevious.setOnClickListener(this);
        ivPlayPause.setOnClickListener(this);
        ivNext.setOnClickListener(this);
        ivRepeat.setOnClickListener(this);
    }

    private void initDataSong() {
        currentTrack = MusicPlayer.getCurrentSongPlay();
        if (currentTrack == null)
            return;
        overflowcounter = 0;
        if (mUpdateProgress != null) {
            sbProgress.removeCallbacks(mUpdateProgress);
        }
        Picasso.with(this).load(Utils.getAlbumArtUri(currentTrack.getIdAlbum())).error(R.drawable.bgk_player_256).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                img_album.setImageBitmap(bitmap);
                setBlurredAlbumArt blurredAlbumArt = new setBlurredAlbumArt();
                blurredAlbumArt.execute(bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {


            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
        tvAlbum.setText(currentTrack.getAlbum());
        tvSong.setText(currentTrack.getName());
        tvArtist.setText(currentTrack.getArtist());
        tvCurrentTime.setText(Utils.formatTime(MusicPlayer.getCurrentPositionPlay()));
        tvTotalTime.setText(Utils.formatTime(currentTrack.getDuration()));
        long time = currentTrack.getDuration();
        sbProgress.setMax((int) time);
        sbProgress.setProgress((int)(MusicPlayer.getCurrentPositionPlay()));

        //setup ui first create
        setUiPlayPause();
        sbProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b) {
                    MusicPlayer.seekSongPlayto(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    //seekbar
    public Runnable mUpdateProgress = new Runnable() {

        @Override
        public void run() {

            long position = MusicPlayer.getCurrentPositionPlay();
            if (sbProgress != null) {
                sbProgress.setProgress((int) position);

                tvCurrentTime.setText(Utils.formatTime(position));
            }
            overflowcounter--;
            if (MusicPlayer.isSongPlaying()) {
                int delay = (int) (1500 - (position % 1000));
                if (overflowcounter < 0) {
                    overflowcounter++;
                    sbProgress.postDelayed(mUpdateProgress, delay);
                }
            }

        }
    };

    private void setDefaultImage() {
        img_album.setImageResource(R.drawable.bgk_player_256);
        Bitmap bg_default = BitmapFactory.decodeResource(getResources(), R.drawable.bgk_player_256);
        setBlurredAlbumArt blurredAlbumArt = new setBlurredAlbumArt();
        blurredAlbumArt.execute(bg_default);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;

            case R.id.iv_shuffle:

                break;

            case R.id.iv_previous:
                actionPreve();
                break;

            case R.id.iv_play_pause:
                actionTongglePause();
                break;

            case R.id.iv_next:
                actionNext();
                break;

            case R.id.iv_repeat:

                break;

        }
    }

    public void actionTongglePause() {
        if (currentTrack == null)
            return;

        NavigationUtils.sendBroadCastWithAction(PlayerActivity.this, PlayTrackService.TOGGLEPAUSE_ACTION);

    }

    public void actionPreve() {
        MusicPlayer.prev();
    }

    public void actionNext() {
        MusicPlayer.next();

    }


    public void setUiPlayPause() {

        if (MusicPlayer.isSongPlaying()) {
            ivPlayPause.setImageResource(R.drawable.ic_pause_white_36dp);
            ivPlayPause.setBackgroundResource(R.drawable.bgk_circle);
            if (mUpdateProgress != null) {
                sbProgress.removeCallbacks(mUpdateProgress);
            }
            sbProgress.postDelayed(mUpdateProgress, 10);
        } else {
            ivPlayPause.setImageResource(R.drawable.ic_play_white_36dp);
            ivPlayPause.setBackgroundResource(R.drawable.bgk_circle_blue);
            if (mUpdateProgress != null) {
                sbProgress.removeCallbacks(mUpdateProgress);
            }
        }
    }

    private class setBlurredAlbumArt extends AsyncTask<Bitmap, Void, Drawable> {

        @Override
        protected Drawable doInBackground(Bitmap... loadedImage) {
            Drawable drawable = null;
            try {
                drawable = Utils.createBlurredImageFromBitmap(loadedImage[0], PlayerActivity.this, 12);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return drawable;
        }

        @Override
        protected void onPostExecute(Drawable result) {
            if (result != null) {
                if (mBlurredArt.getDrawable() != null) {
                    final TransitionDrawable td =
                            new TransitionDrawable(new Drawable[]{
                                    mBlurredArt.getDrawable(),
                                    result
                            });
                    mBlurredArt.setImageDrawable(td);
                    td.startTransition(200);

                } else {
                    mBlurredArt.setImageDrawable(result);
                }
            }
        }

        @Override
        protected void onPreExecute() {
        }
    }

    private class ReceiveStartSong extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(PlayTrackService.START_PLAY_NEW_SONG)) {
                initDataSong();
            }
            if (intent.getAction().equals(PlayTrackService.TOGGLEPAUSE_ACTION)) {
                setUiPlayPause();

            }
            if (intent.getAction().equals(PlayTrackService.PREVIOUS_ACTION)) {

                //initDataSong();

            }
            if (intent.getAction().equals(PlayTrackService.NEXT_ACTION)) {
              //  initDataSong();
            }
        }
    }
}
