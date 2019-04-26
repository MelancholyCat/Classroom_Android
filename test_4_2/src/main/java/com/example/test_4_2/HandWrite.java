package com.example.test_4_2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class HandWrite extends View {
    /*定义类变量*/
    Paint paint = null; //画笔
    /*Bitmap */
    Bitmap originalBitmap = null; //存放原始图像
    Bitmap new1_Bitmap = null; //存放从原始图像复制的位图图像
    Bitmap new2_Bitmap = null; //存放处理后的图像
    float startX = 0,startY = 0; //画线的起点坐标
    float clickX = 0,clickY = 0; //画线的终点坐标
    boolean isMove = true; //设置是否画线的标记
    boolean isClear = false; //设置是否清除涂鸦的标记  /*之前一开始一直闪退就是因为这个设置成了true*/
    int color = Color.RED; //设置画笔的颜色
    float strokeWidth = 6.0f; //设置画笔的宽度
    /*带参数的构造函数*/
    public HandWrite(Context context, AttributeSet attrs){
        super(context,attrs);
        /*从资源中获取原始图像*/
        originalBitmap = BitmapFactory
                .decodeResource(getResources(),R.drawable.cy)
                .copy(Bitmap.Config.ARGB_8888,true);
        System.out.print(originalBitmap.toString());
        /*建立原始图像的位图*/
        new1_Bitmap = Bitmap.createBitmap(originalBitmap);
    }
    /*清除涂鸦*/
    public void clear(){
        isClear = true;
        new2_Bitmap = Bitmap.createBitmap(originalBitmap);
        invalidate();
    }
    /*设置画笔的宽度*/
    public void setstyle(float strokeWidth){
        this.strokeWidth = strokeWidth;
    }
    /*显示绘图*/

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(HandWriting(new1_Bitmap),0,0,null);
    }
    /*记录绘制图形*/
    public Bitmap HandWriting(Bitmap o_Bitmap){
        /*定义画布*/
        Canvas canvas = null;
        /*创建绘制新图形的画布*/
        if (isClear){
            canvas = new Canvas(new2_Bitmap);
        }else {
            canvas = new Canvas(o_Bitmap);/*创建绘制原图形的画布*/
        }
        /*定义画笔*/
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setColor(color);
        paint.setStrokeWidth(strokeWidth);
        if (isMove){
            canvas.drawLine(startX,startY,clickX,clickY,paint); //在画布上画线条
        }
        startX = clickX;
        startY = clickY;
        if (isClear){
            return new2_Bitmap;
        }else
            return o_Bitmap;
    }
    /*定义触摸屏事件*/
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /*获取触摸坐标位置*/
        clickX = event.getX();
        clickY = event.getY();
        if (event.getAction() == MotionEvent.ACTION_DOWN){/*按下屏幕时无绘图*/
            isMove = false;
            invalidate();
            return true;
        }else if (event.getAction() == MotionEvent.ACTION_MOVE){
            /*记录在屏幕上滑动的轨迹*/
            isMove = true;
            invalidate();
            return true;
        }
        return super.onTouchEvent(event);
    }
}
