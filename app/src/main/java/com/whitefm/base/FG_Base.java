// (c)2016 Flipboard Inc, All Rights Reserved.

package com.whitefm.base;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whitefm.R;

import butterknife.ButterKnife;
import rx.Subscription;

public abstract class FG_Base extends Fragment {
    protected Subscription subscription;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getFgRes(), container, false);
        ButterKnife.bind(this, view);
        afterViews();
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unsubscribe();
    }

    protected void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
    protected abstract int getTitleRes();
    protected abstract int getFgRes();
    protected abstract void afterViews();
}
