package com.example.test_4_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private HandWrite handWrite = null;
    private Button button = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handWrite = (HandWrite)findViewById(R.id.handWriteView);//关联View组件
        button = (Button)findViewById(R.id.clear);
        button.setOnClickListener(new mClick());
    }
    /*点击事件*/
    private class mClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            handWrite.clear();//清屏
        }
    }
}
