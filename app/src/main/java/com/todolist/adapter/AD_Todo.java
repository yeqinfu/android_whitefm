// (c)2016 Flipboard Inc, All Rights Reserved.

package com.todolist.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rengwuxian.rxjavasamples.model.Item;
import com.whitefm.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AD_Todo extends RecyclerView.Adapter {
    List<String> images;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent, false);
        return new DebounceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DebounceViewHolder debounceViewHolder = (DebounceViewHolder) holder;
        String image = images.get(position);
      //  Glide.with(holder.itemView.getContext()).load(image.imageUrl).into(debounceViewHolder.imageIv);
        debounceViewHolder.descriptionTv.setText(image);
    }

    @Override
    public int getItemCount() {
        return images == null ? 0 : images.size();
    }

    public void setItems(List<String> images) {
        this.images = images;
        notifyDataSetChanged();
    }

    static class DebounceViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.descriptionTv) TextView descriptionTv;
        public DebounceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
