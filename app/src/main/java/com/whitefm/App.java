// (c)2016 Flipboard Inc, All Rights Reserved.

package com.whitefm;

import android.app.Application;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
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
		Utils_Constant.base_url = getResources().getString(R.string.base_url) + "/web_api";
		Utils_Constant.logger_swicth = getResources().getString(R.string.logger_swicth).equals("true") ? true : false;
		Logger.init("日志打印") // default PRETTYLOGGER or use just init()
				.methodCount(3) // default 2
				.hideThreadInfo() // default shown
				.logLevel(Utils_Constant.logger_swicth==true?LogLevel.FULL:LogLevel.NONE) // default LogLevel.FULL
				.methodOffset(2); // default 0
				//.logAdapter(new AndroidLogAdapter()); //default AndroidLogAdapter

	}
}
