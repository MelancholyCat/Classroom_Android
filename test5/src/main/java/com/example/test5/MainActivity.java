package com.example.test5;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn;
    LinearLayout com;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView)findViewById(R.id.text) ;
        btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new mClick());
    }
    class mClick implements View.OnClickListener{
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        @Override
        public void onClick(View v) {

            com = (LinearLayout)getLayoutInflater()   //从另外布局中关联组件
                    .inflate(R.layout.com, null);
            dialog.setTitle("计算器").setView(com);
            dialog.setPositiveButton("确定", new loginClick());
            dialog.setNegativeButton("退出", new exitClick());
            dialog.setIcon(R.drawable.img1);
            dialog.create();
            dialog.show();

        }
    }
    /*  输入对话框的“确定”按钮事件   */
    class loginClick implements  DialogInterface.OnClickListener
    {
        EditText num1,num2;
        RadioButton rb1,rb2,rb3,rb4;
        @Override
        public void onClick(DialogInterface dialog, int which)
        {
            num1 = (EditText)com.findViewById(R.id.num1);
            num2 = (EditText)com.findViewById(R.id.num2);
            rb1 = (RadioButton)com.findViewById(R.id.rb1);
            rb2 = (RadioButton)com.findViewById(R.id.rb2);
            rb3 = (RadioButton)com.findViewById(R.id.rb3);
            rb4 = (RadioButton)com.findViewById(R.id.rb4);
            try {
                Double n1 = Double.valueOf(num1.getText().toString());
                Double n2 = Double.valueOf(num2.getText().toString());
                Double num = new Double(0);
                if (rb1.isChecked()){
                    num=n1+n2;
                }else if (rb2.isChecked()){
                    num=n1-n2;
                }else if (rb3.isChecked()){
                    num=n1*n2;
                }else if (rb4.isChecked()){
                    num=n1/n2;
                }
                text.setText(num.toString());
            }catch (Exception e){
                text.setText(e.toString());
            }
            dialog.dismiss();   //关闭对话框
        }
    }
    /*  输入对话框的“退出”按钮事件   */
    class exitClick implements DialogInterface.OnClickListener
    {
        @Override
        public void onClick(DialogInterface dialog, int which)
        {
            MainActivity.this.finish(); //点击“退出”按钮退出MainActivity程序
        }
    }
}
