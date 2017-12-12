package com.qjf.sample.model.http.interceptor;

import com.qjf.sample.utils.SystemUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * description: 有网络的Interceptor
 * author: qiaojingfei
 * date: 2017/11/1 下午9:40
 */
public class NetInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        if (SystemUtils.isNetworkConnected()) {
            int maxAge = 60;
            // 有网络时, 缓存, 最大保存时长为60秒(maxAge为0就是不缓存，因为缓存时间为0秒)
            response.newBuilder()
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                    .build();
        } else {
            // 无网络时，设置使用缓存为7天
            int maxStale = 60 * 60 * 24 * 7;
            response.newBuilder()
                    //only-if-cached表示不进行网络请求，完全只使用缓存，若缓存不命中，则返回503错误
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .removeHeader("Pragma")
                    .build();
        }

        //如果没有网络，这个拦截器不做处理，直接返回
        return response;
    }
}
