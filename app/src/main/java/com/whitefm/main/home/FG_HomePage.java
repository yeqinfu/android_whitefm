// (c)2016 Flipboard Inc, All Rights Reserved.

package com.whitefm.main.home;

import com.orhanobut.logger.Logger;
import com.whitefm.R;
import com.whitefm.base.FG_Base;
import com.whitefm.basefm.FG_BaseFM;
import com.whitefm.main.adapter.AD_HomePage;
import com.whitefm.main.api.API;
import com.whitefm.main.bean.BN_HomePageBody;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FG_HomePage extends FG_BaseFM {
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.gridRv)
    RecyclerView gridRv;


    AD_HomePage adapter = new AD_HomePage();
    Observer<BN_HomePageBody> observer = new Observer<BN_HomePageBody>() {
        @Override
        public void onCompleted() {
            Logger.d("onCompleted");

        }

        @Override
        public void onError(Throwable e) {
            Logger.d("onError");
            swipeRefreshLayout.setRefreshing(false);
            Toast.makeText(getActivity(), R.string.loading_failed, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNext(BN_HomePageBody body) {
            swipeRefreshLayout.setRefreshing(false);
            adapter.setImages(body.resultData);
        }
    };

    private void loadContent() {
        subscription = API.getInstance().getAPI().getAllMusic()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    protected int getFgRes() {
        return R.layout.fg_homepage;
    }

    @Override
    protected void afterViews() {
        gridRv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        gridRv.setAdapter(adapter);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        swipeRefreshLayout.setEnabled(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.setImages(null);
                swipeRefreshLayout.setRefreshing(true);
                loadContent();
            }
        });
        loadContent();
    }
}
