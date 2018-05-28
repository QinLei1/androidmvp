package com.example.administrator.bluetoothdemo;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Administrator on 2018/5/15.
 */

public class NewFragment extends Fragment{

    private ListView lvList;
    private TextView tvEmpty;
    private ArrayList<BluetoothDevice> bluetoothDevices;
    private MainActivity activity;

    public static NewFragment getInstance(){
        NewFragment bondFragment = new NewFragment();
        return bondFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) getActivity();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new1, container, false);
        lvList = (ListView) view.findViewById(R.id.lvList);
        tvEmpty = (TextView) view.findViewById(R.id.tvEmpty);
        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                BluetoothDevice bluetoothDevice = bluetoothDevices.get(i);
                activity.showProgressDialog("连接设备中...");
                connectDevice(bluetoothDevice);

            }
        });
        return view;
    }

    private void connectDevice(BluetoothDevice bluetoothDevice) {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        boolean discovering = bluetoothAdapter.isDiscovering();
        if(discovering){
            bluetoothAdapter.cancelDiscovery();
        }
        try {
            final String SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB";
            UUID uuid = UUID.fromString(SPP_UUID);
            BluetoothSocket socket;
            socket = bluetoothDevice.createInsecureRfcommSocketToServiceRecord(uuid);
            socket.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        activity.hideProgressDialog();
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onBluetoothListEvent(BluetoothListEvent bluetoothListEvent){
        MainActivity activity = (MainActivity) getActivity();
        activity.hideProgressDialog();
        bluetoothDevices = bluetoothListEvent.getmList();
        if(bluetoothDevices.size()==0){
            tvEmpty.setVisibility(View.VISIBLE);
            lvList.setVisibility(View.GONE);
        }else{
            tvEmpty.setVisibility(View.GONE);
            lvList.setVisibility(View.VISIBLE);
            BluetoothDevicesAdapter adapter = new BluetoothDevicesAdapter(getActivity(), bluetoothDevices);
            lvList.setAdapter(adapter);

        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
