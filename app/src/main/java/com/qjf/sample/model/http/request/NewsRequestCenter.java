package com.qjf.sample.model.http.request;

import com.qjf.sample.model.http.response.NewsHttpResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by qiaojingfei on 2017/11/17.
 */

public interface NewsRequestCenter {
    String APPKEY = "3a5cd0e5bf8139c108e4f617d57441f3";

    @GET("index")
    Observable<NewsHttpResponse> newsList(@Query("type") String type, @Query("key") String key);

}
