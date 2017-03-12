package com.weibo.bilibiliusedemo.presenters;

import com.orhanobut.logger.Logger;
import com.weibo.bilibiliusedemo.models.VideoInfo;
import com.weibo.bilibiliusedemo.utils.FileUtil;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by weibo on 17-3-12.
 */

public class MainActivityContractPresenterImlp implements MainActivityContact.Presenter {

    private MainActivityContact.View view;

    public MainActivityContractPresenterImlp(MainActivityContact.View view) {
        view.setPresenter(this);
        this.view = view;
    }

    @Override
    public void onStart() {
        view.addToDisposableList(Observable.just(1)
                .map(new Function<Integer, List<VideoInfo>>() {
                    @Override
                    public List<VideoInfo> apply(@NonNull Integer integer) throws Exception {
                        return FileUtil.scanVideoFile();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<VideoInfo>>() {
                    @Override
                    public void accept(@NonNull List<VideoInfo> videoInfos) throws Exception {
                        view.onUpateData(videoInfos);
                    }
                }));
    }
}
