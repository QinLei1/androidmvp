package com.example.administrator.builder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="MainActivity" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //android设计模式-----构建者（Builder）模式:一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。

        Person.PersonBuilder builder = new Person.PersonBuilder();
        Person person = builder.age(1)
                .height("1230")
                .weight("11")
                .name("111")
                .build();

    }

}
