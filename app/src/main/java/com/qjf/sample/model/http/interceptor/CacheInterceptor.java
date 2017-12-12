package com.qjf.sample.model.http.interceptor;

import com.qjf.sample.utils.SystemUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * description: 无网络的interceptor
 * author: qiaojingfei
 * date: 2017/11/1 下午9:39
 */

public class CacheInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!SystemUtils.isNetworkConnected()) {
            request = request.newBuilder()
                    //如果没有网络,从缓存获取数据
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
        Response response = chain.proceed(request);
        return response;
    }
}
