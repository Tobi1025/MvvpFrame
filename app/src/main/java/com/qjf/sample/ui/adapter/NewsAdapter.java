package com.qjf.sample.ui.adapter;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.qjf.sample.databinding.NewsItemBinding;
import com.qjf.sample.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by qiaojingfei on 2017/12/1.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<Map<String, String>> data = new ArrayList<>();
    private Context context;

    public NewsAdapter(List<Map<String, String>> data, Context context) {
        this.data = data;
        this.context = context;
    }


    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_item, parent, false);
        NewsViewHolder viewHolder = new NewsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        holder.binding.setMap(data.get(position));
        holder.binding.executePendingBindings();
    }

    @BindingAdapter({"itemNewsImg"})
    public static void setItemImg(ImageView imageView, String imgUrl) {
        Glide.with(imageView.getContext()).load(imgUrl).crossFade().into(imageView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {

        private final NewsItemBinding binding;

        public NewsViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
