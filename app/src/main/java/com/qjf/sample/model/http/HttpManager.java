package com.qjf.sample.model.http;

import com.qjf.sample.BuildConfig;
import com.qjf.sample.application.BaseApplication;
import com.qjf.sample.application.Constants;
import com.qjf.sample.model.http.interceptor.CacheInterceptor;
import com.qjf.sample.model.http.interceptor.NetInterceptor;
import com.qjf.sample.model.http.request.NewsRequestCenter;
import com.qjf.sample.model.http.request.RequestCenter;
import com.qjf.sample.utils.LogGloble;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by qiaojingfei on 2017/10/31.
 */

public class HttpManager {
    private static OkHttpClient okHttpClient;
    private static RequestCenter requestCenter;
    private static NewsRequestCenter newsRequestCenter;
    private static String baseUrl = SystemConfig.HOST;
    private static String newsUrl = SystemConfig.News_Host;

    public static RequestCenter getRequestCenter() {
        initOkhttp();
        if (requestCenter == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            requestCenter = retrofit.create(RequestCenter.class);
        }
        return requestCenter;
    }

    public static NewsRequestCenter getNewsRequestCenter() {
        initOkhttp();
        if (newsRequestCenter == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(newsUrl)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            newsRequestCenter = retrofit.create(NewsRequestCenter.class);
        }
        return newsRequestCenter;
    }


    private static void initOkhttp() {
        if (okHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        LogGloble.e("Httplog",message);
                    }
                });
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(loggingInterceptor);
            }
            String fileUrl = BaseApplication.getInstance().getCacheDir().getAbsolutePath();
            LogGloble.e("fileUrl===",fileUrl);
            File cacheFile = new File(BaseApplication.getInstance().getCacheDir(),Constants.NET_CACHE);
            Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
            //设置缓存
            builder.addNetworkInterceptor(new NetInterceptor());
            builder.addInterceptor(new CacheInterceptor());
            builder.cache(cache);
            //设置超时
            builder.connectTimeout(10, TimeUnit.SECONDS);
            builder.readTimeout(20, TimeUnit.SECONDS);
            builder.writeTimeout(20, TimeUnit.SECONDS);
            //错误重连
            builder.retryOnConnectionFailure(true);
            okHttpClient = builder.build();
        }
    }

}
