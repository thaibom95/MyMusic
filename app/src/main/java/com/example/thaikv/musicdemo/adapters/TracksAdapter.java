package com.example.thaikv.musicdemo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.thaikv.musicdemo.R;
import com.example.thaikv.musicdemo.models.ItemTracks;
import com.example.thaikv.musicdemo.models.SongMusicStruct;
import com.example.thaikv.musicdemo.utils.Utils;

import java.util.ArrayList;

public class TracksAdapter extends BaseAdapter<SongMusicStruct, TracksAdapter.TracksViewHolder> {
    public TracksAdapter(Context context, RecyclerView recyclerView) {
        super(context, recyclerView);
    }

    @Override
    public void bindBaseViewHolder(TracksViewHolder holder, final int position, ArrayList<SongMusicStruct> arrayList, Context context, final OnClickItemListener onClickItemListener) {
        holder.rllParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItemListener.onClickItemListener(position);
            }
        });

        holder.tvNameSong.setText(arrayList.get(position).getName());
        holder.tvArtist.setText(arrayList.get(position).getArtist());
        holder.tvTime.setText(Utils.formatTime(arrayList.get(position).getDuration()));
    }

    @Override
    public TracksViewHolder createBaseViewHolder(ViewGroup parent, LayoutInflater layoutInflater) {
        return new TracksViewHolder(layoutInflater.inflate(R.layout.item_tracks, parent, false));
    }

    class TracksViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout rllParent;
        TextView tvNameSong;
        TextView tvArtist;
        TextView tvTime;
        ImageView ivMore;

        TracksViewHolder(View itemView) {
            super(itemView);
            rllParent = itemView.findViewById(R.id.rll_parent);
            tvNameSong = itemView.findViewById(R.id.tv_name_song);
            tvArtist = itemView.findViewById(R.id.tv_artist);
            tvTime = itemView.findViewById(R.id.tv_time);
            ivMore = itemView.findViewById(R.id.iv_more);
        }
    }

}

