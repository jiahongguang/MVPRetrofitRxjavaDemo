package com.dy.mvpretrofitdemo.service.view;

import com.dy.mvpretrofitdemo.service.entity.Book;

/**
 * describe:
 * Created by xiaojia ON 2019/1/17.
 */
public interface BookView extends View {
    void onSuccess(Book book);

    void onFail(String result);
}
