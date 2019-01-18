package com.dy.mvpretrofitdemo.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dy.mvpretrofitdemo.R;
import com.dy.mvpretrofitdemo.service.entity.Book;
import com.dy.mvpretrofitdemo.service.presenter.BookPresenter;
import com.dy.mvpretrofitdemo.service.view.BookView;

public class MainActivity extends BaseActivity<BookView, BookPresenter> implements View.OnClickListener, BookView {
    private Button button;
    private TextView textView;
    private BookPresenter bookPresenter;

    @Override
    protected void setContent() {
        super.setContent();
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initListener() {
        button.setOnClickListener(this);
    }

    private void initView() {
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                //请求网络
                bookPresenter.getSearchBooks("金瓶梅", null, 0, 1);
                break;
        }
    }

    @Override
    protected BookPresenter createPresenter() {
        return bookPresenter = new BookPresenter(this);
    }

    @Override
    public void onSuccess(Book book) {
        textView.setText(book.toString());
    }

    @Override
    public void onFail(String result) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注意请求完数据要及时清掉这个订阅关系，不然会发生内存泄漏
        bookPresenter.onStop();
    }
}
