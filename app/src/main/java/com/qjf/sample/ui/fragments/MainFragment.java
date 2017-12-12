package com.qjf.sample.ui.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qjf.sample.R;
import com.qjf.sample.base.BaseFragment;
import com.qjf.sample.databinding.FragmentMainBinding;
import com.qjf.sample.presenter.MainPresenter;
import com.qjf.sample.presenter.contract.MainContract;
import com.qjf.sample.ui.adapter.NewsAdapter;
import com.qjf.sample.utils.EventUtil;

import java.util.List;
import java.util.Map;

public class MainFragment extends BaseFragment<MainPresenter> implements MainContract.IMainView {

    private FragmentMainBinding binding;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter(new MainPresenter());
        if (getArguments() != null) {
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        View view = binding.getRoot();
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(binding.getRoot());
        }
        return view;
    }

    @Override
    public void showContent(List<Map<String, String>> data) {
        NewsAdapter adapter = new NewsAdapter(data, mContext);
        recyclerView = binding.rcyList;
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void lazyLoadData() {
        mPresenter.getDataList();
    }

    @Override
    public void showError(String msg) {
        EventUtil.getInstance().showToast(mContext, msg);
    }
}
