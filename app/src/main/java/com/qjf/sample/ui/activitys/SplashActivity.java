package com.qjf.sample.ui.activitys;

import android.os.Bundle;

import com.qjf.sample.R;
import com.qjf.sample.application.Constants;
import com.qjf.sample.base.BaseActivity;
import com.qjf.sample.utils.MySharePreferece;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * description: 闪屏页
 * author: qiaojingfei
*/
public class SplashActivity extends BaseActivity {

    private boolean isLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setSkipLogic();
    }

    private void setSkipLogic() {
        isLogin = MySharePreferece.getInstence(this).getBoolean(Constants.ISLOGIN);
        Observable.timer(2000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
//                        if (!isLogin) {
//                            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
//                            finish();
//                            return;
//                        }
                        //直接进入主页
                        goToMainAct();
                    }
                });
    }

    public void goToMainAct() {
        MainActivity.start(this);
        finish();
    }


}
