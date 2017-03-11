package com.weibo.bilibiliusedemo.utils;

import android.view.View;

/**
 * Created by weibo on 17-3-5.
 */

public class ViewUtil {

    public static <T extends View> T findView(View view,int id){
        return (T)view.findViewById(id);
    }

}
