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
import com.example.thaikv.musicdemo.models.ItemFolders;

import java.util.ArrayList;

public class FoldersAdapter extends BaseAdapter<ItemFolders, FoldersAdapter.TracksViewHolder> {
    public FoldersAdapter(Context context, RecyclerView recyclerView) {
        super(context, recyclerView);
    }

    @Override
    public void bindBaseViewHolder(TracksViewHolder holder, final int position, ArrayList<ItemFolders> arrayList, Context context, final OnClickItemListener onClickItemListener) {
        holder.rllParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItemListener.onClickItemListener(position);
            }
        });

        holder.tvNameFolder.setText(arrayList.get(position).getNameFolder());
        if (arrayList.get(position).isFile()) {
            holder.ivThumbnail.setImageResource(R.drawable.ic_folder_open);
        } else {
            holder.ivThumbnail.setImageResource(R.drawable.ic_folder_back);
        }
    }

    @Override
    public TracksViewHolder createBaseViewHolder(ViewGroup parent, LayoutInflater layoutInflater) {
        return new TracksViewHolder(layoutInflater.inflate(R.layout.item_folders, parent, false));
    }

    class TracksViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout rllParent;
        TextView tvNameFolder;
        ImageView ivThumbnail;
        ImageView ivMore;

        TracksViewHolder(View itemView) {
            super(itemView);
            rllParent = itemView.findViewById(R.id.rll_parent);
            tvNameFolder = itemView.findViewById(R.id.tv_time);
            ivThumbnail = itemView.findViewById(R.id.iv_thumbnail);
            ivMore = itemView.findViewById(R.id.iv_more);
        }
    }

}

