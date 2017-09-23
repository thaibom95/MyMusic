package com.example.thaikv.musicdemo.widget;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RemoteViews;

import com.example.thaikv.musicdemo.R;
import com.example.thaikv.musicdemo.services.PlayTrackService;
import com.example.thaikv.musicdemo.utils.NavigationUtils;
import com.example.thaikv.musicdemo.utils.Utils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Created by KienPC on 09/21/17.
 */

public class SmallWidget extends BaseWidget {
    @Override
    void onViewsUpdate(Context context, final RemoteViews remoteViews, ComponentName serviceName, Bundle extras) {
        remoteViews.setOnClickPendingIntent(R.id.image_next, PendingIntent.getService(
                context,
                REQUEST_NEXT,
                new Intent(context, PlayTrackService.class)
                        .setAction(PlayTrackService.NEXT_ACTION)
                        .setComponent(serviceName),
                0
        ));
        remoteViews.setOnClickPendingIntent(R.id.image_playpause, PendingIntent.getService(
                context,
                REQUEST_PLAYPAUSE,
                new Intent(context, PlayTrackService.class)
                        .setAction(PlayTrackService.TOGGLEPAUSE_ACTION)
                        .setComponent(serviceName),
                0
        ));
        if (extras != null) {
            String t = extras.getString("track");
            if (t != null) {
                remoteViews.setTextViewText(R.id.textView_title, t);
            }
            t = extras.getString("artist");
            if (t != null) {
                remoteViews.setTextViewText(R.id.textView_subtitle, t);
            }
            remoteViews.setImageViewResource(R.id.image_playpause,
                    extras.getBoolean("playing") ? R.drawable.ic_pause_white_36dp : R.drawable.ic_play_white_36dp);
            long albumId = extras.getLong("albumid");
            if (albumId != -1) {
                Uri uri_image = Utils.getAlbumArtUri(albumId);
                Picasso.with(context).load(uri_image).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        remoteViews.setImageViewBitmap(R.id.imageView_cover, bitmap);
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {
                        remoteViews.setImageViewResource(R.id.imageView_cover, R.drawable.ic_empty_music2);
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });

            }
        }
        remoteViews.setOnClickPendingIntent(R.id.textView_title, PendingIntent.getActivity(
                context,
                0,
                NavigationUtils.getHomeIntent(context),
                PendingIntent.FLAG_UPDATE_CURRENT
        ));

    }

    @Override
    int getLayoutRes() {
        return R.layout.widget_small;
    }
}
