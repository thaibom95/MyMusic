package com.example.thaikv.musicdemo.utils;

import android.content.Context;
import android.content.Intent;

import com.example.thaikv.musicdemo.activitys.MainActivity;

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
}
