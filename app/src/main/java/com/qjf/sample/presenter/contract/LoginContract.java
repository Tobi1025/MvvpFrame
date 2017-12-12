package com.qjf.sample.presenter.contract;

import com.qjf.sample.base.BasePresenter;
import com.qjf.sample.base.BaseView;
import com.qjf.sample.ui.activitys.LoginActivity;

/**
 * description: 关联Presenter与View
 * author: qiaojingfei
 * date: 2017/11/7 下午4:02
 */

public interface LoginContract {
    interface IView extends BaseView {
        String getUsetName();
        String getPassword();
    }

    interface ILoginPresent extends BasePresenter<IView> {
        void setContext(LoginActivity context);
        void login();
        void forgotPassword();
    }
}
