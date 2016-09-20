// (c)2016 Flipboard Inc, All Rights Reserved.

package com.whitefm.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import rx.Subscription;

/**
 * 功能性base 不放业务base逻辑
 */
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
        ButterKnife.unbind(this);
        unsubscribe();
    }

    protected void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
    protected abstract int getFgRes();
    protected abstract void afterViews();
}
