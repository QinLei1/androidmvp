package com.example.administrator.mvp.Presenter;

import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2018/5/28.
 */

public abstract class BasePresenter<V> {

    private static final String TAG = "BasePresenter";
    protected WeakReference<V> mViewRef;

    public void attachView(V view) {
        Log.e(TAG,"attachView");
        mViewRef = new WeakReference<>(view);
    }

    public void detachView() {

        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
            Log.e(TAG,"detachView");
        }
    }

    protected V getView() {
        return mViewRef.get();
    }

}
