package com.weibo.bilibiliusedemo.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weibo.base.util.ViewUtil;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {

    private View view;

    public BaseFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(), container, false);
        initView();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getUserVisibleHint()) {
            loadData();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && view != null) {
            loadData();
        }
    }

    protected void initView() {
    }

    protected void loadData() {
    }

    protected abstract int getLayoutId();

    protected <T extends View> T findView(int id) {
        return (T) view.findViewById(id);
    }

}
