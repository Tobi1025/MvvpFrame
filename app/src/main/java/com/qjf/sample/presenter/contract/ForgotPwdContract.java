package com.qjf.sample.presenter.contract;

import com.qjf.sample.base.BasePresenter;
import com.qjf.sample.base.BaseView;
import com.qjf.sample.ui.activitys.ForgotPwdActivity;

/**
 * Created by qiaojingfei on 2017/11/8.
 */

public interface ForgotPwdContract {
    interface IForgotPwdView extends BaseView{
        String getMobile();
    }

    interface IForgotPwdPresenter extends BasePresenter<IForgotPwdView> {
        void setContext(ForgotPwdActivity context);
        void setTopBar();
        void getVertifCode();
    }
}
