package com.qjf.sample.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.qjf.sample.utils.LogGloble;

/**
 * Created by qiaojingfei on 2017/12/1.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment {
    public void setPresenter(T mPresenter) {
        this.mPresenter = mPresenter;
    }

    protected T mPresenter;

    private boolean isViewCreated;
    protected boolean isLoadData;//如果想保留加载数据，则重写onDestroyView(),将isLoadData置为true
    protected Context mContext;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }


    private void lazyPreLoadData() {
        LogGloble.e("http_frag",getUserVisibleHint()+"&&"+isLoadData+"&&"+isViewCreated);
        if (getUserVisibleHint() && !isLoadData && isViewCreated) {
            isLoadData = true;
            lazyLoadData();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        isViewCreated = true;
        lazyPreLoadData();
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            lazyPreLoadData();
        }
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        isViewCreated = false;
        super.onDestroyView();
    }

    protected void lazyLoadData() {
    }

}
