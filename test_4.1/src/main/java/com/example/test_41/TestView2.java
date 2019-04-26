package com.example.test_41;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

public class TestView2 extends View {
    public TestView2(Context context, AttributeSet attrs){
        super(context,attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.YELLOW);
    }
}
