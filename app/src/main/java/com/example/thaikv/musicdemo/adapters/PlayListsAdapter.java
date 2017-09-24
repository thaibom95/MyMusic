package com.example.thaikv.musicdemo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.thaikv.musicdemo.R;
import com.example.thaikv.musicdemo.models.PlayListsMusicStruct;
import com.simplecityapps.recyclerview_fastscroll.views.FastScrollRecyclerView;

import java.util.ArrayList;

public class PlayListsAdapter extends BaseAdapter<PlayListsMusicStruct, PlayListsAdapter.TracksViewHolder> {
    public PlayListsAdapter(Context context, FastScrollRecyclerView recyclerView) {
        super(context, recyclerView);
    }

    @Override
    public void bindBaseViewHolder(TracksViewHolder holder, final int position, ArrayList<PlayListsMusicStruct> arrayList, Context context, final OnClickItemListener onClickItemListener) {
        holder.rllParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItemListener.onClickItemListener(position);
            }
        });

        holder.tvNamePlayList.setText(arrayList.get(position).getNamePlayList());

    }

    @Override
    public TracksViewHolder createBaseViewHolder(ViewGroup parent, LayoutInflater layoutInflater) {
        return new TracksViewHolder(layoutInflater.inflate(R.layout.item_genres_and_playlists, parent, false));
    }

    @NonNull
    @Override
    public String getSectionName(int position) {
        return getArrayList().get(position).getNamePlayList().charAt(0) + "";
    }

    class TracksViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout rllParent;
        TextView tvNamePlayList;
        ImageView ivMore;

        TracksViewHolder(View itemView) {
            super(itemView);
            rllParent = itemView.findViewById(R.id.rll_parent);
            tvNamePlayList = itemView.findViewById(R.id.tv_title);
            ivMore = itemView.findViewById(R.id.iv_more);
        }
    }

}

