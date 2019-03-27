package com.example.androidexample3_6;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

public class MainActivity extends Activity {

    Button btn1,btn2,btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        btn1.setOnClickListener(new mClick());
        btn2.setOnClickListener(new mClick());
        btn3.setOnClickListener(new mClick());
    }
    class mClick implements View.OnClickListener{
        int m_year = 2019;
        int m_month = 1;
        int m_day = 1;
        int m_hour = 12;
        int m_minute = 1;
        @Override
        public void onClick(View v) {
            if (v==btn1){
                ProgressDialog d = new ProgressDialog(MainActivity.this);
                d.setTitle("进度对话框");
                d.setIndeterminate(true);
                d.setMessage("程序正在Loading...");
                d.setCancelable(true);
                d.setMax(10);
                d.show();
            }else if (v==btn2){
//            设置日期监听器
                DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        m_year = year;
                        m_month = month;
                        m_day = dayOfMonth;
                    }
                };
                //                    创建日期对话框对象
                DatePickerDialog date = new DatePickerDialog(MainActivity.this,dateListener,m_year,m_month,m_day);
                date.setTitle("日期对话框");
                date.show();
            }else if (v==btn3){
                TimePickerDialog.OnTimeSetListener timeListener=new TimePickerDialog.OnTimeSetListener(){
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        m_hour = hourOfDay;
                        m_minute = minute;
                    }
                };
                TimePickerDialog d=new TimePickerDialog(MainActivity.this,timeListener,m_hour,m_minute,true);
                d.setTitle("时间对话框");
                d.show();
            }
        }
    }
}

