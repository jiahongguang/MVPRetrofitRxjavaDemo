package com.dy.mvpretrofitdemo.service.presenter;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * describe:
 * Created by xiaojia ON 2019/1/17.
 */
public abstract class BasePresenter<T> {
    //V表示持有view的引用
    private WeakReference<T> weakRefView;

    protected Reference<T> mViewRef;//view接口类型的弱引用

    public void attachView(T view) {
        mViewRef = new WeakReference<T>(view);//建立关联
    }

    protected T getView() {
        return mViewRef.get();
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    public abstract void onStop();
}
