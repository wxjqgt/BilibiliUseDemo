package com.weibo.bilibiliusedemo.presenters;

import com.weibo.bilibiliusedemo.BasePresenter;
import com.weibo.bilibiliusedemo.BaseView;
import com.weibo.bilibiliusedemo.models.VideoInfo;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by weibo on 17-3-12.
 */

public interface MainActivityContact {
    interface View extends BaseView<Presenter>{
        void onUpateData(List<VideoInfo> data);
    }
    interface Presenter extends BasePresenter{

    }
}
