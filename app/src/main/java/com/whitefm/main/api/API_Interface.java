package com.whitefm.main.api;

import com.whitefm.main.bean.BN_HomePageBody;
import com.whitefm.main.bean.BN_RobotBody;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by yeqinfu on 8/24/16.
 */
public interface API_Interface {

    @GET("MusicController/getAllMusic")
    Observable<BN_HomePageBody> getAllMusic();

    @GET("iqa/query")
    Observable<BN_RobotBody> getAnswer(@Query("appkey") String appkey,
                                       @Query("question") String question
    );


}
