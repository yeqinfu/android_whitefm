package com.whitefm.utils;

import android.content.Context;

/**
 * Created by yeqinfu on 2016/4/7.
 */
public class Utils_System {
    /**
     * 获取通知栏高度
     * @return
     */
    public static  int getStatusBarHeight(Context mContext) {
        int result = 0;
        int resourceId = mContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = mContext.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
