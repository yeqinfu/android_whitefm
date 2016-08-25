// (c)2016 Flipboard Inc, All Rights Reserved.

package com.whitefm;

import android.app.Application;

import com.whitefm.base.Utils_Constant;

public class App extends Application {
    private static App INSTANCE;

    public static App getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        Utils_Constant.base_url=getResources().getString(R.string.base_url);
    }
}
