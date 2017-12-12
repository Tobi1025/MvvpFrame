package com.qjf.sample.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.qjf.sample.application.Constants;

/**
 * Created by Administrator on 2016/8/15.
 */

public class MySharePreferece {
    private static MySharePreferece instence = null;
    private Context mContext;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    public static synchronized MySharePreferece getInstence(Context context) {
        if (instence == null) {
            instence = new MySharePreferece(context);
        }
        return instence;
    }

    private MySharePreferece(Context context) {
        mContext = context;
        sharedPreferences = mContext.getSharedPreferences(Constants.ISLOGIN, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public void saveBoolean(String key, Boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void saveInt(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public void saveFloat(String key, float value) {
        editor.putFloat(key, value);
        editor.commit();
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    public int getInt(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    public float getFloat(String key) {
        return sharedPreferences.getFloat(key, 0);
    }

    public boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }
}
