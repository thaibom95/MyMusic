package com.example.thaikv.musicdemo.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thaikv.musicdemo.R;

public class SplashActivity extends BaseActivity{
    private ImageView ivIconApp;
    private TextView tvNameApp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initViews();
        startAnimation();
    }

    private void initViews() {
        ivIconApp = (ImageView) findViewById(R.id.iv_icon_app);
        tvNameApp = (TextView) findViewById(R.id.tv_name_app);
    }

    private void startAnimation() {
        Animation startIcon = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.anim_start_splash);
        Animation startName = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.anim_start_splash);

        ivIconApp.startAnimation(startIcon);
        tvNameApp.startAnimation(startName);

        startIcon.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }
}
