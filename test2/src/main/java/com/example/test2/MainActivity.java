package com.example.test2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    TextView txt;
    Button btn_pre,btn_next;
    //存放图片id的数组
    private int[] imgs={
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4
    };
    private String[] strs={
            "圆头圆脑——虎头虎脑",
            "粉色头发——四叶草头",
            "发如针叶——青青草地",
            "陈彦良"
    };
    int index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
    }

    void Init(){
        img = (ImageView)findViewById(R.id.img);
        txt = (TextView)findViewById(R.id.descriptionText);
        btn_next = (Button)findViewById(R.id.btn_next);
        btn_pre = (Button)findViewById(R.id.btn_pre);
        btn_pre.setOnClickListener(new mClick());
        btn_next.setOnClickListener(new mClick());
    }

    class mClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if(v==btn_pre){
                if (index>0&&index<imgs.length){
                    index-=1;
                    img.setImageResource(imgs[index]);
                    txt.setText(strs[index]);
                }else {index=0;}
            }
            if(v==btn_next){
                if (index>=0&&index<imgs.length-1){
                    index+=1;
                    img.setImageResource(imgs[index]);
                    txt.setText(strs[index]);
                }else {index=imgs.length-1;}
            }
        }

    }
}
