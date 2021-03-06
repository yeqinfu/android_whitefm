// (c)2016 Flipboard Inc, All Rights Reserved.

package com.whitefm.main.adapter;

import java.util.List;

import com.bumptech.glide.Glide;
import com.whitefm.R;
import com.whitefm.main.bean.BN_HomePage;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AD_HomePage extends RecyclerView.Adapter {
    List<BN_HomePage> images;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        return new DebounceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DebounceViewHolder debounceViewHolder = (DebounceViewHolder) holder;
        BN_HomePage image = images.get(position);
        Glide.with(holder.itemView.getContext()).load(image.getPic_address()).into(debounceViewHolder.imageIv);
        debounceViewHolder.descriptionTv.setText(image.getContent());
    }

    @Override
    public int getItemCount() {
        return images == null ? 0 : images.size();
    }

    public void setImages(List<BN_HomePage> images) {
        this.images = images;
        notifyDataSetChanged();
    }

    static class DebounceViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imageIv) ImageView imageIv;
        @Bind(R.id.descriptionTv) TextView descriptionTv;
        public DebounceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
