package com.whitefm.main.api;

import com.whitefm.base.Utils_Constant;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yeqinfu on 8/24/16.
 */
public class API {
    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();
    private static API_Interface api_interface;
    private volatile static API instance;
    private API(){}
    public static API getInstance(){
        if (instance==null){
            synchronized (API.class){
                if (instance==null){
                    instance=new API();
                }
            }
        }
        return instance;
    }



    public API_Interface getAPI() {
        if (api_interface == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(Utils_Constant.base_url+"/")
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            api_interface = retrofit.create(API_Interface.class);
        }
        return api_interface;
    }

}
