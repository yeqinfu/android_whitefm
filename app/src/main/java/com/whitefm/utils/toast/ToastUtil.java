package com.whitefm.utils.toast;

import android.content.Context;

public class ToastUtil {

	public static void toast(Context context, String toastMsg){
		ToastUtilAdapter.getInstance(context).toast(toastMsg);
	}
	
	public static void toast(Context context, int resId){
		ToastUtilAdapter.getInstance(context).toast(resId);
	}
	
}
