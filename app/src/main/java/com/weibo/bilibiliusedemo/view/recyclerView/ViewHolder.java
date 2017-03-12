package com.weibo.bilibiliusedemo.view.recyclerView;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/7/9.
 */
public class ViewHolder extends RecyclerView.ViewHolder {

    private View itemView;
    private Context context;

    private SparseArrayCompat<View> views = new SparseArrayCompat<>();

    public ViewHolder(View itemView,Context context) {
        super(itemView);
        this.itemView = itemView;
        this.context = context;
    }

    public static ViewHolder createViewHolder(Context context,View itemView){
        ViewHolder holder = new ViewHolder(itemView,context);
        return holder;
    }

    public static ViewHolder createViewHolder(Context context, int LaoutId, ViewGroup parent){
        View view = LayoutInflater.from(context).inflate(LaoutId,parent,false);
        ViewHolder holder = new ViewHolder(view, context);
        return holder;
    }

    public <T extends View> T getView(int id){
        View view = views.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
            views.put(id,view);
        }
        return (T) view;
    }

    public void setText(int id,String text){
        TextView textView = getView(id);
        textView.setText(text);
    }

    public void setImage(int id, Bitmap bitmap){
        ImageView imageView = getView(id);
        imageView.setImageBitmap(bitmap);
    }

}
