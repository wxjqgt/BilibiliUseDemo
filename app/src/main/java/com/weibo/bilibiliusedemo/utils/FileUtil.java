package com.weibo.bilibiliusedemo.utils;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;

import com.orhanobut.logger.Logger;
import com.weibo.bilibiliusedemo.MainApp;
import com.weibo.bilibiliusedemo.models.VideoInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weibo on 17-3-11.
 */

public class FileUtil {

    public static List<VideoInfo> scanVideoFile() {
        List<VideoInfo> list = null;
        ContentResolver contentResolver = MainApp.context_App.getContentResolver();
        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        if (cursor != null) {
            list = new ArrayList<>();
            VideoInfo videoInfo;
            int data = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            int displayName = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME);
            while (cursor.moveToNext()) {
                videoInfo = new VideoInfo();
                videoInfo.setFilePath(cursor.getString(data));
                videoInfo.setDisplayName(cursor.getString(displayName));
                list.add(videoInfo);
            }
        }
        cursor.close();
        return list;
    }

    /**
     * 获取视频的缩略图
     * 先通过ThumbnailUtils来创建一个视频的缩略图，然后再利用ThumbnailUtils来生成指定大小的缩略图。
     * 如果想要的缩略图的宽和高都小于MICRO_KIND，则类型要使用MICRO_KIND作为kind的值，这样会节省内存。
     *
     * @param videoPath 视频的路径
     * @param width     指定输出视频缩略图的宽度
     * @param height    指定输出视频缩略图的高度度
     * @param kind      参照MediaStore.Images(Video).Thumbnails类中的常量MINI_KIND和MICRO_KIND。
     *                  其中，MINI_KIND: 512 x 384，MICRO_KIND: 96 x 96
     * @return 指定大小的视频缩略图
     */
    public static Bitmap getVideoThumbnail(String videoPath, int width, int height, int kind) {
        Bitmap bitmap = null;
        // 获取视频的缩略图
        bitmap = ThumbnailUtils.createVideoThumbnail(videoPath, kind);
        if (bitmap != null) {
            bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
                    ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        }
        return bitmap;
    }

}
