package com.qjf.sample.base;

import android.os.Bundle;


/**
 * description: MVP Activity基类
 * author: qiaojingfei
 * date: 2017/11/7 下午5:38
 */

public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity {

    public void setPresenter(T mPresenter) {
        this.mPresenter = mPresenter;
    }

    protected T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (mPresenter != null)
            mPresenter.attachView(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

}
