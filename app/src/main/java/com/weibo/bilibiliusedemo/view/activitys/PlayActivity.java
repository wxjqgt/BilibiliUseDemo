package com.weibo.bilibiliusedemo.view.activitys;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.weibo.bilibiliusedemo.R;
import com.weibo.bilibiliusedemo.view.BaseActivity;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.provider.MediaStore;
import io.vov.vitamio.widget.VideoView;

public class PlayActivity extends BaseActivity {

    private VideoView videoView;
    private String videoPath;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        playVideo(intent);
    }

    @Override
    protected void loadData() {

        Intent intent = getIntent();
        if (intent == null) {
            return;
        }

        playVideo(intent);
    }

    private void playVideo(Intent intent) {
        if (intent == null) {
            return;
        }
        videoPath = intent.getStringExtra(MainActivity.VIDEO_PATH);
        videoView.setVideoPath(videoPath);
        videoView.start();
    }

    @Override
    protected void initView() {
        videoView = findView(R.id.buffer);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Vitamio.initialize(this);
        if (!LibsChecker.checkVitamioLibs(this)) {
            return;
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_play;
    }

}
