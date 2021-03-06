package com.whitefm.main.home;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.whitefm.R;
import com.whitefm.base.OnLoadMoreListener;
import com.whitefm.base.Utils_Constant;
import com.whitefm.basefm.FG_BaseFM;
import com.whitefm.main.adapter.AD_BaiduMusic;
import com.whitefm.main.api.Http;
import com.whitefm.main.bean.BN_BaiduMusicBody;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yeqinfu on 9/20/16.
 */
public class FG_BaiduMusic extends FG_BaseFM {


    @Bind(R.id.gridRv)
    RecyclerView gridRv;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private int page = 1;
    private int pageSize = 10;

    AD_BaiduMusic ad_baiduMusic = new AD_BaiduMusic(getActivity());

    @Override
    protected int getFgRes() {
        return R.layout.fg_baidu_music;
    }

    @Override
    protected void afterViews() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        gridRv.setLayoutManager(gridLayoutManager);
        gridRv.setAdapter(ad_baiduMusic);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        swipeRefreshLayout.setEnabled(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ad_baiduMusic.setData(null);
                swipeRefreshLayout.setRefreshing(true);
                loadContent(true);
            }
        });

        gridRv.setOnScrollListener(new OnLoadMoreListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                loadContent(false);

            }
        });

        loadContent(true);
    }


    private void loadContent(boolean isRefresh) {
        if (isRefresh) {
            page = 1;
            song_list.clear();
        }
        subscription = Http.getInstance().getAPI(Utils_Constant.baidu_music_base_url)
                .getBaiduMusicList(2, pageSize, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    private List<BN_BaiduMusicBody.SongListBean> song_list = new ArrayList<>();

    Observer<BN_BaiduMusicBody> observer = new Observer<BN_BaiduMusicBody>() {
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
        public void onNext(BN_BaiduMusicBody body) {
            swipeRefreshLayout.setRefreshing(false);
            song_list.addAll(body.getSong_list());
            ad_baiduMusic.setData(song_list);
            page++;


        }
    };


}
