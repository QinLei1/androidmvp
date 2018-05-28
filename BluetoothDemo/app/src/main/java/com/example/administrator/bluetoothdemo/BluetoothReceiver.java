package com.example.administrator.bluetoothdemo;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

//当发现蓝牙设备时,系统会发出三个类型的广播,ACTION_DISCOVERY_STARTED  ACTION_DISCOVERY_FINISHED ACTION_FOUND
public class BluetoothReceiver extends BroadcastReceiver {
    private ArrayList<BluetoothDevice> mList = new ArrayList<>();
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        switch (action){
            case BluetoothAdapter.ACTION_DISCOVERY_STARTED:

                break;
            case BluetoothAdapter.ACTION_DISCOVERY_FINISHED:
                Toast.makeText(context, "搜索完毕..", Toast.LENGTH_SHORT).show();
                break;
            case BluetoothDevice.ACTION_FOUND:
             //   Toast.makeText(context, "搜索到蓝牙设备", Toast.LENGTH_SHORT).show();
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if(device.getBondState()!=BluetoothDevice.BOND_BONDED){
                    mList.add(device);
                    EventBus.getDefault().post(new BluetoothListEvent(mList));
                }

                break;
        }
    }
}
