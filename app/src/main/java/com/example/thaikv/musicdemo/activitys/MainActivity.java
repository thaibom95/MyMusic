package com.example.thaikv.musicdemo.activitys;


import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.view.View;
import android.widget.LinearLayout;

import com.ToxicBakery.viewpager.transforms.ZoomOutSlideTransformer;
import com.example.thaikv.musicdemo.R;
import com.example.thaikv.musicdemo.adapters.ViewPagerAdapter;
import com.example.thaikv.musicdemo.controllers.MusicPlayer;
import com.example.thaikv.musicdemo.models.SongMusicStruct;
import com.example.thaikv.musicdemo.services.PlayTrackService;
import com.example.thaikv.musicdemo.utils.NavigationUtils;
import com.example.thaikv.musicdemo.utils.Utils;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {
    private static String TAG = "MainActivity";
    private static final int REQUEST_CODE_PERMISSION = 100;


    private Toolbar toolbar;
    private FloatingActionButton fab;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private SmartTabLayout smartTabLayout;
    private ViewPager viewPager;
    Toolbar toolbarPlayer;
    private CircleImageView civ_thumbnail;
    private TextView tv_name_song;
    private ImageView iv_play_pause;
    private TextView tv_artist;
    private ProgressBar progressBarPlay;
    private BroadcastReceiver receivSong = new ReceiveStartSong();
    private SongMusicStruct currentSong;
    int overflowcounter = 0;
    private LinearLayout lnlParentPlayerMini;

    ServiceConnection serviceConnect = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            getCurrentSongAndSetup();
        }


        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermissions();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receivSong);
    }

    private void checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                initViews();
            } else {
                String[] permission = new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE
                };
                ActivityCompat.requestPermissions(this, permission, REQUEST_CODE_PERMISSION);
            }
        } else {
            initViews();
        }
    }

    private void initViews() {
        if (MusicPlayer.mService == null) {
            MusicPlayer.ServiceToken mToken = MusicPlayer.bindToService(this, serviceConnect);
        }
        initToolbar();
        initFloatingButton();
        initDrawerLayout();
        initNavigation();
        initViewPager();
        initTabs();

        IntentFilter filter = new IntentFilter();
        filter.addAction(PlayTrackService.TOGGLEPAUSE_ACTION);
        filter.addAction(PlayTrackService.START_PLAY_NEW_SONG);
        filter.addAction(PlayTrackService.PREVIOUS_ACTION);
        filter.addAction(PlayTrackService.NEXT_ACTION);
        registerReceiver(receivSong, filter);

        initPlayerMini();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbarPlayer = (Toolbar) findViewById(R.id.toolbarPlayer);
        toolbarPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PlayerActivity.class));
            }
        });

        civ_thumbnail = (CircleImageView) findViewById(R.id.civ_thumbnail);
        iv_play_pause = (ImageView) findViewById(R.id.iv_play_pause);
        tv_name_song = (TextView) findViewById(R.id.tv_name_song);
        tv_artist = (TextView) findViewById(R.id.tv_artist);
        progressBarPlay = (ProgressBar) findViewById(R.id.pbg_browser__webview);
        getCurrentSongAndSetup();
        iv_play_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentSong == null)
                    return;
                NavigationUtils.sendBroadCastWithAction(MainActivity.this, PlayTrackService.TOGGLEPAUSE_ACTION);

            }
        });


    }

    public void getCurrentSongAndSetup() {
        currentSong = MusicPlayer.getCurrentSongPlay();
        if (currentSong == null) {
            tv_name_song.setText("");
            tv_artist.setText("");
            progressBarPlay.setMax(100);
            progressBarPlay.setProgress(0);
            toolbarPlayer.setEnabled(false);
            iv_play_pause.setEnabled(false);


        } else {
            Picasso.with(this).load(Utils.getAlbumArtUri(currentSong.getIdAlbum())).error(R.drawable.bgk_player_256).into(civ_thumbnail);
            tv_name_song.setText(currentSong.getName());
            tv_artist.setText(currentSong.getArtist());
            toolbarPlayer.setEnabled(true);
            iv_play_pause.setEnabled(true);
            long time = currentSong.getDuration();
            progressBarPlay.setMax((int) time);
            progressBarPlay.setProgress((int) (MusicPlayer.getCurrentPositionPlay()));
            setUiPlayPause();

        }
    }


    public void setUiPlayPause() {

        if (MusicPlayer.isSongPlaying()) {
            iv_play_pause.setImageResource(R.drawable.ic_pause_white_36dp);
            if (mUpdateProgress != null) {
                progressBarPlay.removeCallbacks(mUpdateProgress);
            }
            progressBarPlay.postDelayed(mUpdateProgress, 10);
        } else {
            iv_play_pause.setImageResource(R.drawable.ic_play_white_36dp);
            if (mUpdateProgress != null) {
                progressBarPlay.removeCallbacks(mUpdateProgress);
            }
        }
    }

    private void initFloatingButton() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    private void initDrawerLayout() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void initNavigation() {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    private void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this, getSupportFragmentManager(), 6);

        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(this);
        viewPager.setOffscreenPageLimit(6);
        viewPager.setPageTransformer(true, new ZoomOutSlideTransformer());
    }

    private void initTabs() {
        smartTabLayout = (SmartTabLayout) findViewById(R.id.tabLayout);
        smartTabLayout.setViewPager(viewPager);
        smartTabLayout.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
    }

    private void initPlayerMini() {
        lnlParentPlayerMini = (LinearLayout) findViewById(R.id.lnl_parent_player_mini);
        lnlParentPlayerMini.setVisibility(View.VISIBLE);
    }


    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    private class ReceiveStartSong extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(PlayTrackService.START_PLAY_NEW_SONG)) {
                getCurrentSongAndSetup();

            }

            if (intent.getAction().equals(PlayTrackService.TOGGLEPAUSE_ACTION)) {

                setUiPlayPause();
            }
        }
    }

    public Runnable mUpdateProgress = new Runnable() {

        @Override
        public void run() {

            long position = MusicPlayer.getCurrentPositionPlay();
            if (progressBarPlay != null) {
                progressBarPlay.setProgress((int) position);
            }
            overflowcounter--;
            if (MusicPlayer.isSongPlaying()) {
                int delay = (int) (1500 - (position % 1000));
                if (overflowcounter < 0) {
                    overflowcounter++;
                    progressBarPlay.postDelayed(mUpdateProgress, delay);
                }
            }

        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        setUiPlayPause();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initViews();
                } else {
                    finish();
                }
                break;

            default:
                break;
        }
    }

}
