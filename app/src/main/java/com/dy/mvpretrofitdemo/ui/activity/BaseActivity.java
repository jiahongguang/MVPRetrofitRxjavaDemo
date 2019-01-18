package com.dy.mvpretrofitdemo.ui.activity;

import android.app.Activity;
import android.os.Bundle;

import com.dy.mvpretrofitdemo.service.presenter.BasePresenter;

/**
 * describe:
 * Created by xiaojia ON 2019/1/17.
 */
public abstract class BaseActivity<V, T extends BasePresenter> extends Activity {
    private T mPresenter;//presenter对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContent();
        mPresenter = createPresenter();//创建presenter
        mPresenter.attachView((V) this);//view与presenter建立关联
    }

    protected void setContent() {

    }

    protected abstract T createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
