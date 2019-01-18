package com.dy.mvpretrofitdemo.service.presenter;

import android.content.Context;

import com.dy.mvpretrofitdemo.service.entity.Book;
import com.dy.mvpretrofitdemo.service.manager.DataManager;
import com.dy.mvpretrofitdemo.service.view.BookView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * describe:
 * Created by xiaojia ON 2019/1/17.
 */
public class BookPresenter extends BasePresenter {

    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private BookView mBookView;
    private Book mBook;

    public BookPresenter(BookView bookView) {
        this.mBookView = bookView;
        manager = new DataManager(mContext);
        mCompositeSubscription = new CompositeSubscription();
    }

    /**
     * 网络请求获取数据
     *
     * @param name
     * @param tag
     * @param start
     * @param count
     */
    public void getSearchBooks(String name, String tag, int start, int count) {
        mCompositeSubscription.add(manager.getSearchBooks(name, tag, start, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Book>() {
                    @Override
                    public void onCompleted() {
                        if (mBook != null) {
                            mBookView.onSuccess(mBook);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mBookView.onFail("请求失败！！");
                    }

                    @Override
                    public void onNext(Book book) {
                        mBook = book;
                    }
                })
        );
    }

    @Override
    public void onStop() {
        if (mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }
}
