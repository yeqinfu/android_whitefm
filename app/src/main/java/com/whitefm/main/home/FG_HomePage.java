// (c)2016 Flipboard Inc, All Rights Reserved.

package com.whitefm.main.home;

import java.util.List;

import com.whitefm.R;
import com.whitefm.base.FG_Base;
import com.whitefm.main.adapter.AD_HomePage;
import com.whitefm.main.api.API;
import com.whitefm.main.bean.BN_HomePage;

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

public class FG_HomePage extends FG_Base {
	@Bind(R.id.swipeRefreshLayout)
	SwipeRefreshLayout			swipeRefreshLayout;
	@Bind(R.id.gridRv)
	RecyclerView				gridRv;



	AD_HomePage					adapter		= new AD_HomePage();
	Observer<List<BN_HomePage>>	observer	= new Observer<List<BN_HomePage>>() {
												@Override
												public void onCompleted() {

                                                }

												@Override
												public void onError(Throwable e) {
													swipeRefreshLayout.setRefreshing(false);
													Toast.makeText(getActivity(), R.string.loading_failed, Toast.LENGTH_SHORT).show();
												}

												@Override
												public void onNext(List<BN_HomePage> images) {
													swipeRefreshLayout.setRefreshing(false);
													 adapter.setImages(images);
												}
											};

	@OnCheckedChanged({ R.id.searchRb1, R.id.searchRb2, R.id.searchRb3, R.id.searchRb4 })
	void onTagChecked(RadioButton searchRb, boolean checked) {
		if (checked) {
			unsubscribe();
			adapter.setImages(null);
			swipeRefreshLayout.setRefreshing(true);
			getAllMusic();
		}
	}


	private void getAllMusic() {
		subscription = API.getInstance().getAPI().getAllMusic()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_elementary, container, false);
		ButterKnife.bind(this, view);
		gridRv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
		gridRv.setAdapter(adapter);
		swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
		swipeRefreshLayout.setEnabled(false);
		return view;
	}

	@Override
	protected int getDialogRes() {
		return R.layout.dialog_elementary;
	}

	@Override
	protected int getTitleRes() {
		return R.string.fg_homepage;
	}
}
