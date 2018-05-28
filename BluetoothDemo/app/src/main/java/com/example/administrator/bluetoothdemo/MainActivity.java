package com.example.administrator.bluetoothdemo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.viewpagerindicator.TabPageIndicator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private BluetoothAdapter bluetoothAdapter;
    private ArrayList<BluetoothDevice> mList = new ArrayList<>();

    private BluetoothReceiver receiver;
    private String[] titles = new String[]{"绑定的设备", "新设备"};
    private TabPageIndicator indicator;
    private ViewPager viewPager;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  EventBus.getDefault().register(this);
        initBluetoothAdapter();
        initView();
        initViewPager();

        initPermission();

    }

    @SuppressLint("InflateParams")
    private void initViewPager() {

        BondFragment bondFragment = BondFragment.getInstance();
        NewFragment newFragment = NewFragment.getInstance();
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(bondFragment);
        fragments.add(newFragment);

        viewPager.setAdapter(new BlueToothAdapter(getSupportFragmentManager(), fragments, titles));
        indicator.setViewPager(viewPager);
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initPermission() {
        // android 6.0以上设备发现新蓝牙时，需加入运行时权限，否则无法监听ACTION_FOUND广播
        if (Build.VERSION.SDK_INT >= 6.0) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    Params.MY_PERMISSION_REQUEST_CONSTANT);
        }
    }

    private void initView() {
        Button btOpen = (Button) findViewById(R.id.btOpen);
        indicator = (TabPageIndicator) findViewById(R.id.indicator);
        viewPager = (ViewPager) findViewById(R.id.pager);
        btOpen.setOnClickListener(this);
    }

    private void initBluetoothAdapter() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btOpen:
                if (hasBluetooth()) {
                    if (!bluetoothAdapter.isEnabled()) {//蓝牙未打开
                        openBluetooth();
                    } else {
                        showProgressDialog("正在发现周围蓝牙设备...");
                        //已打开蓝牙,显示已经绑定的蓝牙设备
                        //  showBondDevices();
                        //查找周围蓝牙设备
                        findBluetoothDevices();
                    }
                } else {
                    Toast.makeText(this, "此设备不支持蓝牙", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Params.REQUEST_ENABLE_BT:
                    showProgressDialog("正在发现周围蓝牙设备...");
                    //已打开蓝牙,显示已经绑定的蓝牙设备
                    //  showBondDevices();
                    //查找周围蓝牙设备
                    findBluetoothDevices();
                    break;
            }
        }
    }

    public void showProgressDialog(String content) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(content);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void hideProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();

        }
    }

    private void findBluetoothDevices() {
        bluetoothAdapter.startDiscovery();
        receiver = new BluetoothReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
        registerReceiver(receiver, intentFilter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case Params.MY_PERMISSION_REQUEST_CONSTANT:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //已经授权
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (receiver != null) {
            unregisterReceiver(receiver);

        }
    }




    //弹出询问是否打开蓝牙窗口
    private void openBluetooth() {
        Intent turnOnBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(turnOnBtIntent, Params.REQUEST_ENABLE_BT);
    }

    //是否支持蓝牙
    private boolean hasBluetooth() {
        return bluetoothAdapter != null;
    }

    private static class Params {
        static final int REQUEST_ENABLE_BT = 10;
        static final int MY_PERMISSION_REQUEST_CONSTANT = 11;
    }
}
