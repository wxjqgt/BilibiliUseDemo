package com.weibo.bilibiliusedemo.models;

import android.graphics.Bitmap;

/**
 * Created by weibo on 17-3-11.
 */

public class VideoInfo {

    private String displayName;
    private String filePath;
    private Bitmap bitmap;

    public VideoInfo(){}

    public VideoInfo(String displayName, String filePath, Bitmap bitmap) {
        this.displayName = displayName;
        this.filePath = filePath;
        this.bitmap = bitmap;
    }

    @Override
    public String toString() {
        return "VideoInfo{" +
                "displayName='" + displayName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", bitmap=" + bitmap +
                '}';
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
