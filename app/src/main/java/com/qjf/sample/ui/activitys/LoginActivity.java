package com.qjf.sample.ui.activitys;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.qjf.sample.R;
import com.qjf.sample.base.BaseMvpActivity;
import com.qjf.sample.databinding.ActivityLoginBinding;
import com.qjf.sample.presenter.LoginPresenter;
import com.qjf.sample.presenter.contract.LoginContract;
import com.qjf.sample.utils.EventUtil;

/**
 * description: 登录
 * author: qiaojingfei
 */
public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginContract.IView, View.OnClickListener {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setPresenter(new LoginPresenter());
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.btnLogin.setOnClickListener(this);
        binding.tvForgotPassword.setOnClickListener(this);
        mPresenter.setContext(this);
    }

    @Override
    public String getUsetName() {
        return binding.edtUsername.getText().toString();
    }

    @Override
    public String getPassword() {
        return binding.edtPassword.getText().toString();
    }


    @Override
    public void showError(String msg) {
        EventUtil.getInstance().showToast(this, msg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                mPresenter.login();
                break;
            case R.id.tv_forgot_password:
                mPresenter.forgotPassword();
                break;
        }
    }
}
