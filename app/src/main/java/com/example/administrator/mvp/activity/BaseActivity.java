package com.example.administrator.mvp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.mvp.Presenter.BasePresenter;

/**
 * Created by Administrator on 2018/5/28.
 */

public abstract class BaseActivity<V,P extends BasePresenter<V>> extends AppCompatActivity {
    public P presenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        presenter.attachView((V)this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
    protected abstract P createPresenter();
}
