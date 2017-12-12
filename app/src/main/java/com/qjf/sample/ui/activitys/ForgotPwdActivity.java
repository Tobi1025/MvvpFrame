package com.qjf.sample.ui.activitys;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.qjf.sample.R;
import com.qjf.sample.base.BaseMvpActivity;
import com.qjf.sample.databinding.ActivityForgotPwdBinding;
import com.qjf.sample.presenter.ForgotPwdPresenter;
import com.qjf.sample.presenter.contract.ForgotPwdContract;
import com.qjf.sample.utils.EventUtil;

/**
 * description: 忘记密码
 * author: qiaojingfei
 * date: 2017/11/8 下午7:12
*/
public class ForgotPwdActivity extends BaseMvpActivity<ForgotPwdPresenter> implements View.OnClickListener, ForgotPwdContract.IForgotPwdView {

    private ActivityForgotPwdBinding binding;

    public ActivityForgotPwdBinding getBinding() {
        return binding;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setPresenter(new ForgotPwdPresenter());
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_pwd);
        setListener();
        mPresenter.setContext(this);
        mPresenter.setTopBar();
    }

    private void setListener() {
        binding.btnSendVerficationCode.setOnClickListener(this);
        binding.topBar.topbarLeftimage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send_verfication_code:
                mPresenter.getVertifCode();
                break;
            case R.id.topbar_leftimage:
                finish();
                break;
        }
    }

    @Override
    public String getMobile() {
        return binding.edtMobile.getText().toString();
    }


    @Override
    public void showError(String msg) {
        EventUtil.getInstance().showToast(this, msg);
    }
}
