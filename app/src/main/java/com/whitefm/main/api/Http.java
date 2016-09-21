package com.whitefm.main.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.whitefm.base.Utils_Constant;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yeqinfu on 8/24/16.
 */
public class Http {
    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();
    private static API api_interface;
    private volatile static Http instance;

    private Http() {
        if (Utils_Constant.logger_swicth == true) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClient = new OkHttpClient.Builder().addInterceptor(logging).build();
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .create();//使用 gson coverter，统一日期请求格式
            gsonConverterFactory = GsonConverterFactory.create(gson);
        }
    }

    public static Http getInstance() {
        if (instance == null) {
            synchronized (Http.class) {
                if (instance == null) {
                    instance = new Http();
                }
            }
        }
        return instance;
    }

    public API getAPI() {
        if (api_interface == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient).baseUrl(Utils_Constant.base_url + "/")
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory).build();
            api_interface = retrofit.create(API.class);
        }
        return api_interface;
    }

    public API getAPI(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient).baseUrl(baseUrl)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory).build();
        api_interface = retrofit.create(API.class);
        return api_interface;
    }


}
