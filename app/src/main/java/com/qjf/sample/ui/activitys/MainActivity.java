package com.qjf.sample.ui.activitys;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.qjf.sample.R;
import com.qjf.sample.application.Constants;
import com.qjf.sample.base.BaseActivity;
import com.qjf.sample.databinding.ActivityMainBinding;
import com.qjf.sample.model.http.HttpManager;
import com.qjf.sample.model.http.response.HttpResponse;
import com.qjf.sample.ui.adapter.HomePagerAdapter;
import com.qjf.sample.ui.fragments.MainFragment;
import com.qjf.sample.ui.fragments.SettingFragment;
import com.qjf.sample.utils.MySharePreferece;
import com.qjf.sample.utils.RxUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private ActivityMainBinding binding;
    private Context mAppContext;
    private Subscription loginSubscription;
    private List<Fragment> fragments = new ArrayList<>();
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mAppContext = getApplicationContext();
        autoLogin();
        init();
    }

    private void init() {
        binding.topBar.topbarTitle.setText("首页");
        viewPager = binding.viewpager;
        MainFragment mainFragment = new MainFragment();
        SettingFragment settingFragment = new SettingFragment();
        fragments.add(mainFragment);
        fragments.add(settingFragment);
        HomePagerAdapter adapter = new HomePagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        binding.rgMenus.setOnCheckedChangeListener(this);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ((RadioButton)binding.rgMenus.getChildAt(position)).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void autoLogin() {
        String userName = MySharePreferece.getInstence(mAppContext).getString(Constants.USER_NAME);
        String md5LoginPwd = MySharePreferece.getInstence(mAppContext).getString(Constants.PASSWORD);
        loginSubscription = HttpManager.getRequestCenter().login(userName, md5LoginPwd)
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

                    }
                }, new Action0() {
                    @Override
                    public void call() {
//                                mView.hidLoading();
                    }
                });
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!loginSubscription.isUnsubscribed()) {
            loginSubscription.unsubscribe();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.rbMessage:
                viewPager.setCurrentItem(0);
                break;
            case R.id.rbSetting:
                viewPager.setCurrentItem(1);
                break;
        }
    }
}
