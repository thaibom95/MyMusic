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
import com.example.thaikv.musicdemo.models.GenresMusicStruct;
import com.simplecityapps.recyclerview_fastscroll.views.FastScrollRecyclerView;

import java.util.ArrayList;

public class GenresAdapter extends BaseAdapter<GenresMusicStruct, GenresAdapter.TracksViewHolder> {
    public GenresAdapter(Context context, FastScrollRecyclerView recyclerView) {
        super(context, recyclerView);
    }

    @Override
    public void bindBaseViewHolder(TracksViewHolder holder, final int position, ArrayList<GenresMusicStruct> arrayList, Context context, final OnClickItemListener onClickItemListener) {
        holder.rllParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItemListener.onClickItemListener(position);
            }
        });

        holder.tvGenres.setText(arrayList.get(position).getName());
    }

    @Override
    public TracksViewHolder createBaseViewHolder(ViewGroup parent, LayoutInflater layoutInflater) {
        return new TracksViewHolder(layoutInflater.inflate(R.layout.item_genres_and_playlists, parent, false));
    }

    @NonNull
    @Override
    public String getSectionName(int position) {
        return getArrayList().get(position).getName().charAt(0) + "";
    }

    class TracksViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout rllParent;
        TextView tvGenres;
        ImageView ivMore;

        TracksViewHolder(View itemView) {
            super(itemView);
            rllParent = itemView.findViewById(R.id.rll_parent);
            tvGenres = itemView.findViewById(R.id.tv_title);
            ivMore = itemView.findViewById(R.id.iv_more);
        }
    }

}

