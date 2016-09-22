// (c)2016 Flipboard Inc, All Rights Reserved.

package com.whitefm;

import android.app.Application;
import android.content.Context;
import android.os.Message;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.whitefm.base.Utils_Constant;
import com.whitefm.base.app.AppExceptionHandler;
import com.whitefm.base.app.Utils_SharedPreferences;

public class App extends Application {
    private static App INSTANCE;

    public static App getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        context = this;
        Utils_Constant.base_url = getResources().getString(R.string.base_url) + "/web_api";
        Utils_Constant.logger_swicth = getResources().getString(R.string.logger_swicth).equals("true") ? true : false;
        Utils_Constant.db_version = getResources().getInteger(R.integer.db_version);
        Logger.init("日志打印") // default PRETTYLOGGER or use just init()
                .methodCount(3) // default 2
                .hideThreadInfo() // default shown
                .logLevel(Utils_Constant.logger_swicth == true ? LogLevel.FULL : LogLevel.NONE) // default LogLevel.FULL
                .methodOffset(2); // default 0
        //.logAdapter(new AndroidLogAdapter()); //default AndroidLogAdapter

        /*****************************************************************
         * 闪退处理
         *****************************************************************/
        AppExceptionHandler mReportAppError = AppExceptionHandler.getInstance();
        mReportAppError.init(this);
        Utils_SharedPreferences sharedPreferences = new Utils_SharedPreferences(context);
        sharedPreferences.put(Utils_Constant.SERVER_TIMESTAMP, 0L);
        //handler.sendEmptyMessage(MSG_REFRESH_SERVER_TIME);

    }

    private final int MSG_REFRESH_SERVER_TIME = 0x101;

    private android.os.Handler handler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_REFRESH_SERVER_TIME:
                    // 获取服务器时间

                    //	handler.sendEmptyMessageDelayed(MSG_REFRESH_SERVER_TIME, 10 * 1000);
                    break;
            }
        }
    };

    @Override
    public void onTerminate() {
        if (null != handler) {
            handler.removeMessages(MSG_REFRESH_SERVER_TIME);
        }
        super.onTerminate();
    }

    private static Context context;

    public static Context getContext() {
        return context;
    }
}
