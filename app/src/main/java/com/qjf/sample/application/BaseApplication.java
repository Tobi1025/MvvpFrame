package com.qjf.sample.application;

import android.app.Application;

/**
 * Created by qiaojingfei on 2017/11/1.
 */

public class BaseApplication extends Application {
    private static BaseApplication application = null;

    public static BaseApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }
}
