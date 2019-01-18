package com.dy.mvpretrofitdemo.service;

import com.dy.mvpretrofitdemo.service.entity.Book;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * describe:
 * Created by xiaojia ON 2019/1/17.
 */
public interface RetrofitService {
    @GET("book/search")
    Observable<Book> getSearchBooks(@Query("q") String name,
                                    @Query("tag") String tag,
                                    @Query("start") int start,
                                    @Query("count") int count);
}
