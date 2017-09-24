package com.example.thaikv.musicdemo.utils;

import android.content.Context;
import android.content.Intent;

import com.example.thaikv.musicdemo.activitys.MainActivity;
import com.example.thaikv.musicdemo.controllers.MusicPlayer;

/**
 * Created by KienPC on 09/21/17.
 */

public class NavigationUtils {


    public static Intent getHomeIntent(Context context) {


        final Intent notificationIntent = new Intent(context, MainActivity.class);

        notificationIntent.setAction(Intent.ACTION_MAIN);
        notificationIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return notificationIntent;

    }

    public static void sendBroadCastWithAction(Context context,String action){
        Intent intent = new Intent(action);
        context.sendBroadcast(intent);
    }
}
