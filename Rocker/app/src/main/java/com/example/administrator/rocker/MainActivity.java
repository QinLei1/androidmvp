package com.example.administrator.rocker;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Rocker rocker = (Rocker) findViewById(R.id.rocker);
        rocker.setRockerCtrl(BitmapFactory.decodeResource(getResources(), R.drawable.ic_rocker_control));
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg_rocker);
        rocker.setRockerBg(bitmap);
        rocker.setRudderListener(new Rocker.RudderListener() {
            @Override
            public void onSteeringWheelChanged(int action, int angle, float x, float y) {
                Log.e(TAG,"action:"+action+"\n"+"angle:"+angle+"\n"+"x:"+x+"\n"+"y:"+y);
            }
        });

    }
}
