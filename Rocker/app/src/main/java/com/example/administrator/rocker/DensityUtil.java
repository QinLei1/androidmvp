package com.example.administrator.rocker;

import android.content.Context;

/**
 * Created by Administrator on 2018/5/14.
 */

public class DensityUtil {
    public static int dip2px(Context context, int dp){
        float density = context.getResources().getDisplayMetrics().density;
       return (int) (density * dp + 0.5f);
    }
}
