package com.example.administrator.bluetoothdemo;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Administrator on 2018/5/15.
 */

public class BondFragment extends Fragment {

    private TextView tvEmptyBond;
    private ListView lvListBond;

    public static BondFragment getInstance() {
        BondFragment bondFragment = new BondFragment();
        return bondFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bond, container, false);
        tvEmptyBond = (TextView) view.findViewById(R.id.tvEmpty);
        lvListBond = (ListView) view.findViewById(R.id.lvList);
        showBondDevices();
        return view;
    }

    /**
     * 显示已经绑定的蓝牙设备
     */
    private void showBondDevices() {
        ArrayList<BluetoothDevice> mList = new ArrayList<>();
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();
        for (BluetoothDevice bluetoothDevice : bondedDevices) {
            mList.add(bluetoothDevice);
        }
        if (mList.size() == 0) {
            tvEmptyBond.setVisibility(View.VISIBLE);
            lvListBond.setVisibility(View.GONE);
        } else {
            tvEmptyBond.setVisibility(View.GONE);
            lvListBond.setVisibility(View.VISIBLE);
            BluetoothDevicesAdapter bluetoothDevicesAdapter = new BluetoothDevicesAdapter(getActivity(), mList);
            lvListBond.setAdapter(bluetoothDevicesAdapter);
        }


    }
}
