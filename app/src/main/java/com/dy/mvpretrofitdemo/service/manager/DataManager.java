package com.dy.mvpretrofitdemo.service.manager;

import android.content.Context;

import com.dy.mvpretrofitdemo.service.RetrofitHelper;
import com.dy.mvpretrofitdemo.service.RetrofitService;
import com.dy.mvpretrofitdemo.service.entity.Book;

import rx.Observable;

/**
 * describe: 更方便的调用RetrofitService中的方法
 * Created by xiaojia ON 2019/1/17.
 */
public class DataManager {
    private RetrofitService mRetrofitService;

    public DataManager(Context context) {
        this.mRetrofitService = RetrofitHelper.getInstance(context).getServer();
    }

    public Observable<Book> getSearchBooks(String name, String tag, int start, int count) {
        return mRetrofitService.getSearchBooks(name, tag, start, count);
    }
}
