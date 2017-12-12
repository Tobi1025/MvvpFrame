package com.qjf.sample.presenter;

import com.qjf.sample.base.RxPresenter;
import com.qjf.sample.model.http.HttpManager;
import com.qjf.sample.model.http.request.NewsRequestCenter;
import com.qjf.sample.model.http.response.NewsHttpResponse;
import com.qjf.sample.presenter.contract.MainContract;
import com.qjf.sample.utils.RxUtil;

import java.util.List;
import java.util.Map;

import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by qiaojingfei on 2017/12/1.
 */

public class MainPresenter extends RxPresenter<MainContract.IMainView> implements MainContract.IMainPresenter {

    @Override
    public void getDataList() {
        Subscription newsSubscription = HttpManager.getNewsRequestCenter().newsList("shehui", NewsRequestCenter.APPKEY)
                .compose(RxUtil.<NewsHttpResponse>rxNewsSchedulerHelper())
                .compose(RxUtil.<Map<String, Object>>handleNewsResult())
                .subscribe(new Action1<Map<String, Object>>() {
                    @Override
                    public void call(final Map<String, Object> res) {
                        if (res != null) {
                            List<Map<String, String>> data = (List<Map<String, String>>) res.get("data");
                            mView.showContent(data);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                }, new Action0() {
                    @Override
                    public void call() {

                    }
                });
        addSubscribe(newsSubscription);
    }
}
