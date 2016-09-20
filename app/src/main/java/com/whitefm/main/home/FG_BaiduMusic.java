package com.whitefm.main.home;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.whitefm.R;
import com.whitefm.basefm.FG_BaseFM;

import butterknife.Bind;

/**
 * Created by yeqinfu on 9/20/16.
 */
public class FG_BaiduMusic extends FG_BaseFM {


    @Bind(R.id.gridRv)
    RecyclerView gridRv;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;



    @Override
    protected int getFgRes() {
        return R.layout.fg_baidu_music;
    }

    @Override
    protected void afterViews() {

    }
}
