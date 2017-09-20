package com.example.thaikv.musicdemo.utils;

import android.app.ActivityManager;
import android.content.Context;

import static android.content.Context.ACTIVITY_SERVICE;

public class Utils {

    public static String formatTime(long milliseconds){
        String finalTimerString = "";
        String secondsString = "";

        // Convert total duration into time
        int hours = (int)( milliseconds / (1000*60*60));
        int minutes = (int)(milliseconds % (1000*60*60)) / (1000*60);
        int seconds = (int) ((milliseconds % (1000*60*60)) % (1000*60) / 1000);
        // Add hours if there
        if(hours > 0){
            finalTimerString = hours + ":";
        }

        // Prepending 0 to seconds if it is one digit
        if(seconds < 10){
            secondsString = "0" + seconds;
        }else{
            secondsString = "" + seconds;}

        finalTimerString = finalTimerString + minutes + ":" + secondsString;

        // return timer string
        return finalTimerString;
    }

    public  static boolean checkServiceRuning(Context context){
        ActivityManager manager = (ActivityManager)  context.getSystemService(ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE))
        {
            if ("com.example.thaikv.musicdemo.services.PlayTrackService"
                    .equals(service.service.getClassName()))
            {
                return true;
            }
        }
        return false;
    }
}
