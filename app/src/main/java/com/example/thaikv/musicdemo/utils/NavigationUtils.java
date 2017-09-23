package com.example.thaikv.musicdemo.utils;

import android.content.Context;
import android.content.Intent;

import com.example.thaikv.musicdemo.activitys.MainActivity;

/**
 * Created by KienPC on 09/21/17.
 */

public class NavigationUtils {


    public static Intent getHomeIntent(Context context) {

        final Intent intent = new Intent(context, MainActivity.class);
        intent.setAction(Constants.HOME_APP);
        return intent;

    }
}
