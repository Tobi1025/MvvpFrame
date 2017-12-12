package com.qjf.sample.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by qiaojingfei on 2017/11/7.
 */

public class EventUtil {
    private EventUtil() {
    }

    private static class SingleInstance {
        private static EventUtil eventUtil = new EventUtil();
    }

    public static EventUtil getInstance() {
        return SingleInstance.eventUtil;
    }

    public void showToast(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }
}
