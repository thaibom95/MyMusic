package com.example.thaikv.musicdemo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.simplecityapps.recyclerview_fastscroll.views.FastScrollRecyclerView;

import java.util.ArrayList;

public abstract class BaseAdapter<T, V extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements FastScrollRecyclerView.SectionedAdapter {
    private static final String TAG = "BaseAdapter";

    private final int VIEW_ITEM = 1;

    private Context mContext;
    private LayoutInflater layoutInflater;
    private ArrayList<T> arrayList;
    private OnClickItemListener mOnClickItemListener;


    public BaseAdapter(final Context context, FastScrollRecyclerView fastScrollRecyclerView) {
        this.mContext = context;
        this.layoutInflater = LayoutInflater.from(context);
        arrayList = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return createBaseViewHolder(parent, layoutInflater);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        bindBaseViewHolder(((V) holder), position, arrayList, mContext, mOnClickItemListener);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return VIEW_ITEM;
    }

    public void setOnClickItemListener(OnClickItemListener onClickItemListener) {
        this.mOnClickItemListener = onClickItemListener;
    }

    public ArrayList<T> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<T> arrayList) {
        this.arrayList = arrayList;
    }

    public abstract void bindBaseViewHolder(V holder, final int position, ArrayList<T> arrayList, Context context, OnClickItemListener onClickItemListener);

    public abstract V createBaseViewHolder(ViewGroup parent, LayoutInflater layoutInflater);

    public interface OnClickItemListener {
        void onClickItemListener(int position);
    }
}

