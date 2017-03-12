package com.weibo.bilibiliusedemo;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.Logger;

/**
 * Created by weibo on 17-3-11.
 */

public class MainApp extends Application {

    public static Context context_App;

    @Override
    public void onCreate() {
        super.onCreate();
        context_App = this;
        Logger.init("weibo");
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        context_App = null;
    }
}
