package com.qjf.sample.model.http.request;


import com.qjf.sample.model.http.response.HttpResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by qiaojingfei on 2017/11/1.
 */

public interface RequestCenter {
    @GET("login")
    Observable<HttpResponse> login(@Query("mobile") String username, @Query("password") String password);

    @GET("retrievePassword")
    Observable<HttpResponse>forgotPwd(@Query("mobile") String mobile);
}
