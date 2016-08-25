package com.whitefm.main.api;

import com.whitefm.main.bean.BN_HomePageBody;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by yeqinfu on 8/24/16.
 */
public interface API_Interface {

    @GET("MusicController/getAllMusic")
    Observable<BN_HomePageBody> getAllMusic();
}
