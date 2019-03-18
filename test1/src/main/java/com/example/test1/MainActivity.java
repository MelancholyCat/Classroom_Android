package com.example.test1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    Button b1,b2;
    CheckBox ch1,ch2,ch3,ch4;
    RadioButton rb1,rb2,rb3,rb4;
    TextView txt1,txt2,txt3,txt4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        b1 = (Button)findViewById(R.id.btn_1);
        b2 = (Button)findViewById(R.id.btn_2);
        ch1 = (CheckBox)findViewById(R.id.cb_1);
        ch2 = (CheckBox)findViewById(R.id.cb_2);
        ch3 = (CheckBox)findViewById(R.id.cb_3);
        ch4 = (CheckBox)findViewById(R.id.cb_4);
        rb1 = (RadioButton)findViewById(R.id.rb_1);
        rb2 = (RadioButton)findViewById(R.id.rb_2);
        rb3 = (RadioButton)findViewById(R.id.rb_3);
        rb4 = (RadioButton)findViewById(R.id.rb_4);
        txt1 = (TextView)findViewById(R.id.ans_text1);
        txt2 = (TextView)findViewById(R.id.ans_text2);
        txt3 = (TextView)findViewById(R.id.ans_text3);
        txt4 = (TextView)findViewById(R.id.ans_text4);

        b1.setOnClickListener(new mClick());
        b2.setOnClickListener(new mClick());
    }

    class mClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            String t1,t2,t3,t4;
            Answer a;
            if (v==b1){
                a = submit();
                t1="您的得分："+a.getScore();
                t2=a.getAns1();
                t3=a.getAns2();
                t4 = "您提交的隐藏信息：喜欢世界杯！";
                txt1.setText(t1);
                txt2.setText(t2);
                txt3.setText(t3);
                txt4.setText(t4);
            }else if (v==b2){
                reset();
            }
        }
    }

    private void reset(){
        Intent intent = new Intent(MainActivity.this,MainActivity.class);
        startActivity(intent);
    }
    private Answer submit(){
        Answer a= new Answer();
        int  score = 0,i=0;
        String ans1 = "您提交的答案一：",ans2 = "您提交的答案二：";
        /*单选*/
        if (rb1.isChecked()){
            score+=1;
            ans1+=" "+rb1.getText();
        }else if (rb2.isChecked()){
            ans1+=" "+rb2.getText();
        }else if (rb3.isChecked()){
            ans1+=" "+rb3.getText();
        }else if (rb4.isChecked()){
            ans1+=" "+rb4.getText();
        }
        /*复选*/
        if (ch1.isChecked()){
            i+=1;
            ans2+=" "+ch1.getText();
        }
        if (ch2.isChecked()){
            i-=1;
            ans2+=" "+ch2.getText();
        }
        if (ch3.isChecked()){
            i+=1;
            ans2+=" "+ch3.getText();
        }
        if (ch4.isChecked()){
            i-=1;
            ans2+=" "+ch4.getText();
        }
        if (i==2){
            score+=1;
        }
        a.set(score,ans1,ans2);
        return a;
    }

}

class Answer{
    private int  score;
    private String ans1,ans2;
    Answer(){
        score = 0;
        ans1 = "";
        ans2 = "";
    }
    Answer(int a,String b,String c){
        score = a;
        ans1 = b;
        ans2 = c;
    }

    public int getScore(){
        return score;
    }
    public String getAns1(){
        return ans1;
    }
    public String getAns2(){
        return ans2;
    }
    public void setAns2(String a){
        ans2 = a;
    }
    public void setAns1(String a){
        ans1 = a;
    }
    public void setAns2(int a){
        score = a;
    }
    public void set(int a,String b,String c){
        score = a;
        ans1 = b;
        ans2 = c;
    }
}