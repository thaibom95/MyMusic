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
import com.example.thaikv.musicdemo.models.AlbumMusicStruct;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AlbumsAdapter extends BaseAdapter<AlbumMusicStruct, AlbumsAdapter.AlbumsViewHolder> {
    public AlbumsAdapter(Context context, RecyclerView recyclerView) {
        super(context, recyclerView);
    }

    @Override
    public void bindBaseViewHolder(AlbumsViewHolder holder, final int position, ArrayList<AlbumMusicStruct> arrayList, Context context, final OnClickItemListener onClickItemListener) {
        holder.rllParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItemListener.onClickItemListener(position);
            }
        });

        holder.tvNameAlbum.setText(arrayList.get(position).getName());
        holder.tvArtist.setText(arrayList.get(position).getArtist());

        Picasso.with(context)
                .load(arrayList.get(position).getUriThumbnail())
                .placeholder(R.drawable.ic_music_default)
                .fit()
                .centerCrop()
                .into(holder.civThumbnail);
    }

    @Override
    public AlbumsViewHolder createBaseViewHolder(ViewGroup parent, LayoutInflater layoutInflater) {
        return new AlbumsViewHolder(layoutInflater.inflate(R.layout.item_albums, parent, false));
    }

    class AlbumsViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout rllParent;
        CircleImageView civThumbnail;
        TextView tvNameAlbum;
        TextView tvArtist;
        ImageView ivMore;

        AlbumsViewHolder(View itemView) {
            super(itemView);
            rllParent = itemView.findViewById(R.id.rll_parent);
            civThumbnail = itemView.findViewById(R.id.civ_thumbnail);
            tvNameAlbum = itemView.findViewById(R.id.tv_name_album);
            tvArtist = itemView.findViewById(R.id.tv_artist);
            ivMore = itemView.findViewById(R.id.iv_more);
        }
    }

}

