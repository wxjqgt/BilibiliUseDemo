package com.weibo.bilibiliusedemo.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by weibo on 17-3-5.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private List<Disposable> disposables;

    private static List<Activity> activities = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activities.add(this);

        setContentView(getLayoutId());
        initView();
        loadData();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finishAndRemoveActivity(this);
        dispos();
    }

    public void addToDisposList(Disposable dispos){
        if (disposables == null){
            disposables = new ArrayList<>();
        }
        disposables.add(dispos);
    }

    private void dispos(){
        if (disposables != null && disposables.size() != 0) {
            int size = disposables.size();
            for (int i = 0; i < size; i++) {
                Disposable disposable = disposables.get(i);
                if (disposable != null && !disposable.isDisposed()) {
                    disposable.dispose();
                }
            }
            disposables.clear();
            disposables = null;
        }
    }

    public static void finishAndRemoveActivity(Activity activity) {
        Iterator<Activity> iterator = activities.iterator();
        while (iterator.hasNext()) {
            Activity iter = iterator.next();
            if (iter == activity) {
                iter.finish();
                iterator.remove();
                return;
            }
        }
    }

    public static void finishAllActivity() {
        if (activities != null && activities.size() != 0) {
            for (int i = 0; i < activities.size(); i++) {
                activities.get(i).finish();
            }
            activities.clear();
            activities = null;
        }
    }

    protected abstract int getLayoutId();

    protected void initView() {
    }

    protected void loadData() {
    }

    protected <T extends View> T findView(int id) {
        return (T) super.findViewById(id);
    }

}
