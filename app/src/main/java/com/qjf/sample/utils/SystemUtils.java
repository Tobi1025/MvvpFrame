package com.qjf.sample.utils;

import android.content.Context;
import android.net.ConnectivityManager;

import com.qjf.sample.application.BaseApplication;

/**
 * Created by qiaojingfei on 2017/11/1.
 */

public class SystemUtils {
    /**
     * 检查是否有可用网络
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) BaseApplication.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }
}
