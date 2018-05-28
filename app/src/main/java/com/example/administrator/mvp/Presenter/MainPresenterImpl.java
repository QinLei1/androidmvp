package com.example.administrator.mvp.Presenter;

import android.content.Context;

import com.example.administrator.mvp.View.MainView;


/**
 * Created by Administrator on 2018/5/28.
 */

public class MainPresenterImpl extends BasePresenter<MainView> implements MainPresenter {

    private MainView mainView;

    public MainPresenterImpl( MainView mainView) {
        this.mainView = mainView;


    }

    @Override
    public void login(String userName, String password) {
        //  context.startActivity(new Intent(context, Main2Activity.class));
        mainView.showToast(userName, password);
    }
}
