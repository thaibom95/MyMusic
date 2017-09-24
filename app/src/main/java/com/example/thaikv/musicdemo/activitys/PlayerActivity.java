package com.example.thaikv.musicdemo.activitys;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.thaikv.musicdemo.R;

import jp.wasabeef.blurry.Blurry;

public class PlayerActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout lnlParent;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        initViews();
        initEvents();
    }

    private void initViews() {
        lnlParent = (LinearLayout) findViewById(R.id.lnl_parent_music_player);
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

//        Blurry.with(this)
//                .radius(10)
//                .sampling(10)
//                .color(Color.argb(26, 255, 255, 0))
//                .async()
//                .animate(1)
//                .onto(lnlParent);

    }

    private void initEvents() {
        ivBack.setOnClickListener(this);
        ivShuffle.setOnClickListener(this);
        ivPrevious.setOnClickListener(this);
        ivPlayPause.setOnClickListener(this);
        ivNext.setOnClickListener(this);
        ivRepeat.setOnClickListener(this);
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

                break;

            case R.id.iv_play_pause:

                break;

            case R.id.iv_next:

                break;

            case R.id.iv_repeat:

                break;

        }
    }
}
