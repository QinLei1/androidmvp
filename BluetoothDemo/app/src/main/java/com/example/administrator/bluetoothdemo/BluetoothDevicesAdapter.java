package com.example.administrator.bluetoothdemo;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/5/15.
 */

public class BluetoothDevicesAdapter extends BaseAdapter {
    private ArrayList<BluetoothDevice> mList = new ArrayList<>();
    private Context context;

    public BluetoothDevicesAdapter(Context context, ArrayList<BluetoothDevice> mList) {
        this.mList = mList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        DeviceViewHolder viewHolder ;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item, null);
            viewHolder = new DeviceViewHolder();
            viewHolder.textDeviceName = (TextView) view.findViewById(R.id.textDeviceName);
            viewHolder.textDeviceNo = (TextView) view.findViewById(R.id.textDeviceNo);
            viewHolder.textDeviceStatus = (TextView) view.findViewById(R.id.textDeviceStatus);
            viewHolder.imageView = (ImageView) view.findViewById(R.id.imageView);
            view.setTag(viewHolder);
        } else {
            viewHolder = (DeviceViewHolder) view.getTag();
        }
        BluetoothDevice bluetoothDevice = mList.get(i);
        String bluetoothDeviceName = bluetoothDevice.getName();
        String address = bluetoothDevice.getAddress();
        int bondState = bluetoothDevice.getBondState();
        if(bondState==BluetoothDevice.BOND_BONDED){
            viewHolder.textDeviceStatus.setText("已绑定");
        }else{
            viewHolder.textDeviceStatus.setText("未绑定");
        }
        viewHolder.textDeviceName.setText(bluetoothDeviceName);
        viewHolder.textDeviceNo.setText(address);

        return view;
    }

    static class DeviceViewHolder {
        private TextView textDeviceNo;
        private TextView textDeviceName;
        private TextView textDeviceStatus;
        private ImageView imageView;

    }
}
