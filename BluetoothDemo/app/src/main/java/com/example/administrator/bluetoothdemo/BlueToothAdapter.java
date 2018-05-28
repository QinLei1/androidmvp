package com.example.administrator.bluetoothdemo;

import android.bluetooth.BluetoothAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by Administrator on 2018/5/15.
 */

public class BlueToothAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> mList ;
    private String[] TITLE ;

    public BlueToothAdapter(FragmentManager fm,ArrayList<Fragment> mList,String[] TITLE) {
        super(fm);
        this.mList = mList;
        this.TITLE = TITLE;
    }

    @Override
    public Fragment getItem(int position) {
        //新建一个Fragment来展示ViewPager item的内容，并传递参数
        return mList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLE[position % TITLE.length];
    }

    @Override
    public int getCount() {
        return TITLE.length;
    }


}
