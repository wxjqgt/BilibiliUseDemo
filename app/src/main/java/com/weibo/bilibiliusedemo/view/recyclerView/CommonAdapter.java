package com.weibo.bilibiliusedemo.view.recyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/9.
 */
public abstract class CommonAdapter<T> extends RecyclerView.Adapter<ViewHolder> {

    private List<T> datas;
    private Context context;
    private int LayoutId;

    public CommonAdapter(Context context, int LayoutId, List<T> datas) {
        this.context = context;
        this.datas = new ArrayList<>();
        this.LayoutId = LayoutId;
        if (datas != null) {
            addDatas(datas);
        }
    }

    public void addDatas(List<T> data) {
        datas.addAll(data);
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.createViewHolder(context, LayoutInflater.from(context).inflate(LayoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        convert(holder, datas.get(position), position);
    }

    public abstract void convert(ViewHolder holder, T t, int position);

    @Override
    public int getItemCount() {
        return datas.size();
    }

}
