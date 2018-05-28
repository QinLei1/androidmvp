package com.example.administrator.bluetoothdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/5/21.
 */

public class Circle extends View {

    private int first_round_color;
    private int second_round_color;
    private int third_round_color;
    private int circle_width;
    private Paint firstRountPaint;

    public Circle(Context context) {
        super(context, null);

    }


    public Circle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);

    }

    public Circle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Circle);
        first_round_color = typedArray.getColor(R.styleable.Circle_first_round_color, 0);//最外圈圆环颜色
        second_round_color = typedArray.getColor(R.styleable.Circle_second_round_color, 0);//第二圈圆环颜色
        third_round_color = typedArray.getColor(R.styleable.Circle_third_round_color, 0);//最里圈圆环颜色
        circle_width = typedArray.getColor(R.styleable.Circle_circle_width, 0);//圆环厚度
        typedArray.recycle();
        init();
    }

    private void init() {
        int circle_stroke_width = dip2px(circle_width);
        //外圈画笔
        firstRountPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        firstRountPaint.setColor(first_round_color);
        firstRountPaint.setStyle(Paint.Style.STROKE);
        firstRountPaint.setStrokeWidth(circle_stroke_width);////圆圈的线条粗细
        firstRountPaint.setStrokeCap(Paint.Cap.SQUARE);//开启显示边缘为圆形




    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF_first = new RectF();
        canvas.drawArc(rectF_first,-30,120,false,firstRountPaint);//画圆环
    }

    private int dip2px(int dip) {
        float density = getResources().getDisplayMetrics().density;
        int px = (int) (dip * density + 0.5f);
        return px;
    }
}
