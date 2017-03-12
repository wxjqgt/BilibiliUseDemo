package com.weibo.bilibiliusedemo;

import io.reactivex.disposables.Disposable;

/**
 * Created by weibo on 17-3-12.
 */

public interface BaseView<T> {
    void addToDisposableList(Disposable disposable);
    void setPresenter(T presenter);
}
