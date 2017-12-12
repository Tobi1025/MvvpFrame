package com.qjf.sample.presenter;

import android.text.TextUtils;

import com.qjf.sample.R;
import com.qjf.sample.base.RxPresenter;
import com.qjf.sample.model.http.HttpManager;
import com.qjf.sample.model.http.response.HttpResponse;
import com.qjf.sample.presenter.contract.ForgotPwdContract;
import com.qjf.sample.ui.activitys.ForgotPwdActivity;
import com.qjf.sample.utils.RxUtil;

import java.util.Map;

import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by qiaojingfei on 2017/11/8.
 */

public class ForgotPwdPresenter extends RxPresenter<ForgotPwdContract.IForgotPwdView> implements ForgotPwdContract.IForgotPwdPresenter {
    ForgotPwdActivity activity;

    @Override
    public void setContext(ForgotPwdActivity context) {
        this.activity = context;
    }

    @Override

    public void setTopBar() {
        activity.getBinding().topBar.topbarTitle.setText("忘记密码");
        activity.getBinding().topBar.topbarLeftimage.setImageResource(R.mipmap.icon_back);
    }

    @Override
    public void getVertifCode() {
        String mobile = mView.getMobile();
        if (TextUtils.isEmpty(mobile)) {
            mView.showError("请输入手机号");
            return;
        }
        Subscription loginSubscription = HttpManager.getRequestCenter().forgotPwd(mobile)
                .compose(RxUtil.<HttpResponse>rxSchedulerHelper())
                .compose(RxUtil.<Map<String, Object>>handleResult())
                .subscribe(new Action1<Map<String, Object>>() {
                    @Override
                    public void call(final Map<String, Object> res) {
                        if (res != null) {

                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError(throwable.getMessage());
                    }
                }, new Action0() {
                    @Override
                    public void call() {
//                                mView.hidLoading();
                    }
                });
        addSubscribe(loginSubscription);
    }

}
