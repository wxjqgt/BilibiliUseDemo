package com.weibo.bilibiliusedemo.view.activitys;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import com.jakewharton.rxbinding2.support.v7.widget.RecyclerViewChildAttachStateChangeEvent;
import com.jakewharton.rxbinding2.support.v7.widget.RxRecyclerView;
import com.jakewharton.rxbinding2.support.v7.widget.RxRecyclerViewAdapter;
import com.weibo.bilibiliusedemo.presenters.MainActivityContractPresenterImlp;
import com.weibo.bilibiliusedemo.R;
import com.weibo.bilibiliusedemo.adapters.VideoDataRvAdapter;
import com.weibo.bilibiliusedemo.models.VideoInfo;
import com.weibo.bilibiliusedemo.presenters.MainActivityContact;
import com.weibo.bilibiliusedemo.utils.ObjectUtil;
import com.weibo.bilibiliusedemo.view.BaseActivity;
import com.weibo.bilibiliusedemo.view.recyclerView.CommonAdapter;
import com.weibo.bilibiliusedemo.view.recyclerView.OnRecyclerViewItemClickListener;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity implements MainActivityContact.View {

    public static final String VIDEO_PATH = "video_path";
    private MainActivityContact.Presenter presenter;
    private List<VideoInfo> videoInfos;
    private CommonAdapter adapter;
    private RecyclerView rv_Video;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void loadData() {
        new MainActivityContractPresenterImlp(this);
        if (ObjectUtil.checkPresenterisNull(presenter)) {
            return;
        }
        presenter.onStart();
    }

    @Override
    public void addToDisposableList(Disposable dispos) {
        addToDisposList(dispos);
    }

    @Override
    public void onUpateData(List<VideoInfo> videoInfos) {
        adapter = new VideoDataRvAdapter(MainActivity.this, R.layout.videodata_item, videoInfos);
        rv_Video.setLayoutManager(new LinearLayoutManager(this));
        rv_Video.setAdapter(adapter);
        this.videoInfos = videoInfos;
    }

    @Override
    protected void initView() {
        rv_Video = findView(R.id.rv_video);
        coordinatorLayout = findView(R.id.coordinatorLayout);

        rv_Video.addOnItemTouchListener(new OnRecyclerViewItemClickListener(rv_Video) {
            @Override
            public void OnItemClickLitener(RecyclerView.ViewHolder viewHolder) {
            if (videoInfos != null && videoInfos.size() != 0){
                Intent intent = new Intent(MainActivity.this,PlayActivity.class);
                intent.putExtra(VIDEO_PATH,videoInfos.get(viewHolder.getAdapterPosition()).getFilePath());
                startActivity(intent);
            }
            }
        });
    }

    @Override
    public void setPresenter(MainActivityContact.Presenter presenter) {
        if (ObjectUtil.checkPresenterisNull(presenter)) {
            return;
        }
        this.presenter = presenter;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

}

