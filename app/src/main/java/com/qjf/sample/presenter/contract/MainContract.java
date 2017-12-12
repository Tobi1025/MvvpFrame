package com.qjf.sample.presenter.contract;

import com.qjf.sample.base.BasePresenter;
import com.qjf.sample.base.BaseView;

import java.util.List;
import java.util.Map;

/**
 * Created by qiaojingfei on 2017/12/1.
 */

public interface MainContract {
    interface IMainView extends BaseView {
        void showContent(List<Map<String, String>> data);
    }

    interface IMainPresenter extends BasePresenter<IMainView> {
        void getDataList();
    }
}
