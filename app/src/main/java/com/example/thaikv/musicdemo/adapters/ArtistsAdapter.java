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
import com.example.thaikv.musicdemo.models.ArtistMusicStruct;

import java.util.ArrayList;

public class ArtistsAdapter extends BaseAdapter<ArtistMusicStruct, ArtistsAdapter.TracksViewHolder> {
    public ArtistsAdapter(Context context, RecyclerView recyclerView) {
        super(context, recyclerView);
    }

    @Override
    public void bindBaseViewHolder(TracksViewHolder holder, final int position, ArrayList<ArtistMusicStruct> arrayList, Context context, final OnClickItemListener onClickItemListener) {
        holder.rllParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItemListener.onClickItemListener(position);
            }
        });

        holder.tvNameArtist.setText(arrayList.get(position).getName());
        holder.tvCountAlbums.setText(arrayList.get(position).getNumberAlbumOfArtist() + " Albums");
        holder.tvCountTracks.setText(arrayList.get(position).getNumberSongOfArtist() + " Tracks");
    }

    @Override
    public TracksViewHolder createBaseViewHolder(ViewGroup parent, LayoutInflater layoutInflater) {
        return new TracksViewHolder(layoutInflater.inflate(R.layout.item_artists, parent, false));
    }

    class TracksViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout rllParent;
        TextView tvNameArtist;
        TextView tvCountAlbums;
        TextView tvCountTracks;
        ImageView ivMore;

        TracksViewHolder(View itemView) {
            super(itemView);
            rllParent = itemView.findViewById(R.id.rll_parent);
            tvNameArtist = itemView.findViewById(R.id.tv_artist);
            tvCountAlbums = itemView.findViewById(R.id.tv_count_albums);
            tvCountTracks = itemView.findViewById(R.id.tv_count_tracks);
            ivMore = itemView.findViewById(R.id.iv_more);
        }
    }

}

