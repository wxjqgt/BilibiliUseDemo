package com.weibo.bilibiliusedemo.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.provider.MediaStore;

import com.weibo.bilibiliusedemo.R;
import com.weibo.bilibiliusedemo.models.VideoInfo;
import com.weibo.bilibiliusedemo.utils.FileUtil;
import com.weibo.bilibiliusedemo.view.activitys.MainActivity;
import com.weibo.bilibiliusedemo.view.recyclerView.CommonAdapter;
import com.weibo.bilibiliusedemo.view.recyclerView.ViewHolder;

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

public class VideoDataRvAdapter extends CommonAdapter<VideoInfo> {

    private MainActivity mActivity;

    public VideoDataRvAdapter(Context context, int LayoutId, List<VideoInfo> datas) {
        super(context, LayoutId, datas);
        this.mActivity = (MainActivity) context;
    }

    @Override
    public void convert(final ViewHolder holder, VideoInfo videoInfo, int position) {
        holder.setText(R.id.video_title, videoInfo.getDisplayName());
        mActivity.addToDisposList(Observable.just(videoInfo.getFilePath())
                .map(new Function<String, Bitmap>() {
                    @Override
                    public Bitmap apply(@NonNull String path) throws Exception {
                        return FileUtil.getVideoThumbnail(path, 100, 100, MediaStore.Video.Thumbnails.MINI_KIND);

                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Bitmap>() {
                    @Override
                    public void accept(@NonNull Bitmap bitmap) throws Exception {
                        holder.setImage(R.id.video_dispaly, bitmap);
                    }
                }));
    }
}
