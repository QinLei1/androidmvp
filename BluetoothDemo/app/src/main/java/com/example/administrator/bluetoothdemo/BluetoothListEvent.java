package com.example.administrator.bluetoothdemo;

import android.bluetooth.BluetoothDevice;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/5/15.
 */

public class BluetoothListEvent {
    private ArrayList<BluetoothDevice> mList ;
    public BluetoothListEvent (ArrayList<BluetoothDevice> mList){
        this.mList = mList;
    }
    public ArrayList<BluetoothDevice> getmList() {
        return mList;
    }

    public void setmList(ArrayList<BluetoothDevice> mList) {
        this.mList = mList;
    }
}
