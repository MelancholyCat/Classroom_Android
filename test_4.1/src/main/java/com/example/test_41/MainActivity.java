package com.example.test_41;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    TestView tView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tView = (TestView) findViewById(R.id.textView1);
        tView.setOnTouchListener(new mOnTouch());

    }

    class mOnTouch implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int x1, y1;
            /*获取坐标位置*/
            x1 = (int) event.getX();
            y1 = (int) event.getY();
            /*在屏幕上点击*/
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                /*按新坐标绘图*/
                tView.getXY(x1, y1);
                tView.invalidate();
                return true;
            } else if (event.getAction() == MotionEvent.ACTION_MOVE) {/*在屏幕上拖动*/
                /*按新坐标绘图*/
                tView.getXY(x1, y1);
                tView.invalidate();
                if (x1>500&&x1<1000&&y1>500&&y1<1000){
                    System.exit(0);
                    return false;
                }
                return true;
            }
            return tView.onTouchEvent(event);
        }
    }
}
