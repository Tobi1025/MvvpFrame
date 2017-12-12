package com.qjf.sample.utils;

import android.text.TextUtils;

import com.qjf.sample.model.exception.ApiException;
import com.qjf.sample.model.http.response.HttpResponse;
import com.qjf.sample.model.http.response.NewsHttpResponse;

import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Description: RxUtil
 * Creator: yxc
 * date: 2016/9/21 18:47
 */
public class RxUtil {

    /**
     * 统一线程处理
     *
     * @return
     */
    public static Observable.Transformer<HttpResponse, HttpResponse> rxSchedulerHelper() {    //compose简化线程
        return new Observable.Transformer<HttpResponse, HttpResponse>() {
            @Override
            public Observable<HttpResponse> call(Observable<HttpResponse> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static Observable.Transformer<NewsHttpResponse, NewsHttpResponse> rxNewsSchedulerHelper() {    //compose简化线程
        return new Observable.Transformer<NewsHttpResponse, NewsHttpResponse>() {
            @Override
            public Observable<NewsHttpResponse> call(Observable<NewsHttpResponse> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 统一返回结果处理
     *
     * @return
     */
    public static Observable.Transformer<HttpResponse, Map<String, Object>> handleResult() {   //compose判断结果
        return new Observable.Transformer<HttpResponse, Map<String, Object>>() {
            @Override
            public Observable<Map<String, Object>> call(Observable<HttpResponse> httpResponseObservable) {
                return httpResponseObservable.flatMap(new Func1<HttpResponse, Observable<Map<String, Object>>>() {
                    @Override
                    public Observable<Map<String, Object>> call(HttpResponse httpResponse) {
                        if (httpResponse == null) {
                            return Observable.error(new ApiException("网络不可用"));
                        } else if (httpResponse.getCode() == 1) {
                            return createData(httpResponse.getResult());
                        } else if (!TextUtils.isEmpty(httpResponse.getMessage())) {
                            return Observable.error(new ApiException("*" + httpResponse.getMessage()));
                        } else {
                            return Observable.error(new ApiException("*" + "服务器异常"));
                        }
                    }
                });
            }
        };
    }

    public static Observable.Transformer<NewsHttpResponse, Map<String, Object>> handleNewsResult() {   //compose判断结果
        return new Observable.Transformer<NewsHttpResponse, Map<String, Object>>() {
            @Override
            public Observable<Map<String, Object>> call(Observable<NewsHttpResponse> httpResponseObservable) {
                return httpResponseObservable.flatMap(new Func1<NewsHttpResponse, Observable<Map<String, Object>>>() {
                    @Override
                    public Observable<Map<String, Object>> call(NewsHttpResponse httpResponse) {
                        if (httpResponse == null) {
                            return Observable.error(new ApiException("网络不可用"));
                        } else if ("成功的返回".equals(httpResponse.getReason())) {
                            return createData(httpResponse.getResult());
                        } else if (!TextUtils.isEmpty(httpResponse.getReason())) {
                            return Observable.error(new ApiException("*" + httpResponse.getReason()));
                        } else {
                            return Observable.error(new ApiException("*" + "服务器异常"));
                        }
                    }
                });
            }
        };
    }


    /**
     * 生成Observable
     *
     * @return
     */
    public static Observable<Map<String, Object>> createData(final Map<String, Object> map) {
        return Observable.create(new Observable.OnSubscribe<Map<String, Object>>() {
            @Override
            public void call(Subscriber<? super Map<String, Object>> subscriber) {
                try {
                    subscriber.onNext(map);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
