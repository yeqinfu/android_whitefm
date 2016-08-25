package com.whitefm.main.api;

import java.util.List;

import com.whitefm.main.bean.BN_HomePage;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by yeqinfu on 8/24/16.
 */
public interface API_Interface {

    @GET("MusicController/getAllMusic")
    Observable<List<BN_HomePage>> getAllMusic();
}
