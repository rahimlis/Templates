package com.rahimlis.templates.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;


public abstract class BaseAdapter<T> extends RecyclerView.Adapter {
    protected ArrayList<T> models;
    protected int resourceId;
    protected Context context;

    public BaseAdapter(ArrayList<T> models, int resourceId, Context context) {
        this.models = models;
        this.resourceId = resourceId;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(resourceId, parent, false);
        return getViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    protected abstract RecyclerView.ViewHolder getViewHolder(View view);


    @Override
    public int getItemCount() {
        if (models == null)
            return 0;
        return models.size();
    }


}
