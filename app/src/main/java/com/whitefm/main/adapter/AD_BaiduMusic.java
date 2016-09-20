// (c)2016 Flipboard Inc, All Rights Reserved.

package com.whitefm.main.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.whitefm.R;
import com.whitefm.main.bean.BN_BaiduMusicBody;
import com.whitefm.main.bean.BN_HomePage;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AD_BaiduMusic extends RecyclerView.Adapter {
    List<BN_BaiduMusicBody.SongListBean> list;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        return new DebounceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DebounceViewHolder debounceViewHolder = (DebounceViewHolder) holder;
        BN_BaiduMusicBody.SongListBean item = list.get(position);
        Glide.with(holder.itemView.getContext()).load(item.getPic_big()).into(debounceViewHolder.imageIv);
        debounceViewHolder.descriptionTv.setText(item.getTitle());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void setData(List<BN_BaiduMusicBody.SongListBean> list) {
        this.list = list;
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
