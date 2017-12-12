package com.qjf.sample.presenter;

import android.content.Intent;
import android.text.TextUtils;

import com.qjf.sample.application.Constants;
import com.qjf.sample.base.RxPresenter;
import com.qjf.sample.encrypt.MD5;
import com.qjf.sample.model.http.HttpManager;
import com.qjf.sample.model.http.response.HttpResponse;
import com.qjf.sample.presenter.contract.LoginContract;
import com.qjf.sample.ui.activitys.ForgotPwdActivity;
import com.qjf.sample.ui.activitys.LoginActivity;
import com.qjf.sample.ui.activitys.MainActivity;
import com.qjf.sample.utils.MySharePreferece;
import com.qjf.sample.utils.RxUtil;

import java.util.Map;

import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by qiaojingfei on 2017/11/7.
 */

public class LoginPresenter extends RxPresenter<LoginContract.IView> implements LoginContract.ILoginPresent {
    private LoginActivity mContext;
    private String userName;
    private String md5LoginPwd;


    @Override
    public void setContext(LoginActivity context) {
        mContext = context;
    }

    @Override
    public void login() {
        userName = mView.getUsetName();
        String passWord = mView.getPassword();
        md5LoginPwd = MD5.GetMD5Code(passWord);
        if (TextUtils.isEmpty(userName)) {
            mView.showError("请输入用户名");
            return;
        }
        if (TextUtils.isEmpty(passWord)) {
            mView.showError("请输入密码");
            return;
        }
        Subscription loginSubscription = HttpManager.getRequestCenter().login(userName, md5LoginPwd)
                .compose(RxUtil.<HttpResponse>rxSchedulerHelper())
                .compose(RxUtil.<Map<String, Object>>handleResult())
                .subscribe(new Action1<Map<String, Object>>() {
                    @Override
                    public void call(final Map<String, Object> res) {
                        if (res != null) {
                            MySharePreferece.getInstence(mContext.getApplicationContext()).saveBoolean(Constants.ISLOGIN, true);
                            MySharePreferece.getInstence(mContext.getApplicationContext()).saveString(Constants.USER_NAME, userName);
                            MySharePreferece.getInstence(mContext.getApplicationContext()).saveString(Constants.PASSWORD, md5LoginPwd);
                            MainActivity.start(mContext);
                            mContext.finish();
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

    @Override
    public void forgotPassword() {
        mContext.startActivity(new Intent(mContext, ForgotPwdActivity.class));
    }
}
