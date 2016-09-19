package com.whitefm.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class AD_Base<T> extends BaseAdapter {
    protected List<T> ts;

    final protected LayoutInflater inflater;

    public AD_Base(Context context) {
        super();
        inflater = LayoutInflater.from(context);
        ts = new ArrayList<T>();
    }

    public void setDatas(List<T> t) {
        if (t != null && t.size() >= 0) {
            this.ts.clear();
            this.ts.addAll(t);
            notifyDataSetChanged();
        }
    }

    public void addDatas(List<T> t) {
        if (t != null && t.size() >= 0) {
            this.ts.addAll(t);
            notifyDataSetChanged();
        }
    }

    public List<T> getTs() {
        return ts;
    }

    @Override
    public int getCount() {
        return ts.size();
    }

    @Override
    public Object getItem(int position) {
        return ts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public T getItemObject(int position) {
        return ts.get(position);
    }
}
