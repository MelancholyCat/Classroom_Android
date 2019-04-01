package com.example.test6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class TestView extends View {
//    定义小球的初始坐标
    int x=150,y=50;

    public TestView(Context context, AttributeSet attrs){
        super(context,attrs);
    }
//    传递小球坐标位置
    void getXY(int _x,int _y){
        x=_x;
        y=_y;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.CYAN);//设置背景颜色为青色
        Paint paint = new Paint();//定义画笔
        paint.setStyle(Paint.Style.FILL);//设置画实心图形
        paint.setAntiAlias(true);/*去锯齿*/
        paint.setColor(Color.BLUE);/*设置画笔颜色*/
        canvas.drawCircle(x,y,30,paint);/*画一个实心圆*/
        paint.setColor(Color.WHITE);/*设置画笔颜色*/
        canvas.drawCircle(x-9,y-9,6,paint);/*画一个实心圆*/
    }


}
