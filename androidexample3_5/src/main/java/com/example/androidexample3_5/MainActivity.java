package com.example.androidexample3_5;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity {
    ProgressDialog mydialog;
    Button btn1,btn2;
    LinearLayout login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1=(Button)findViewById(R.id.button1);
        btn2=(Button)findViewById(R.id.button2);
        btn1.setOnClickListener(new mClick());
        btn2.setOnClickListener(new mClick());
    }
    class mClick implements View.OnClickListener
    {
        AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
        @Override
        public void onClick(View arg0)
        {
            if(arg0 == btn1)
            {
                //设置对话框的标题
                dialog.setTitle("警告");
                //设置对话框的图标
                dialog.setIcon(R.drawable.icon2);
                //设置对话框显示的内容
                dialog.setMessage("本项操作可能导致信息泄漏！");
                //设置对话框的“确定”按钮
                dialog.setPositiveButton("确定",new okClick());
                //创建对象框
                dialog.create();
                //显示对象框
                dialog.show();
            }
            else  if(arg0 == btn2)
            {
                login = (LinearLayout)getLayoutInflater()   //从另外布局中关联组件
                        .inflate(R.layout.login, null);

                dialog.setTitle("用户登录").setMessage("请输入用户名和密码")
                        .setView(login);
                dialog.setPositiveButton("确定", new loginClick());
                dialog.setNegativeButton("退出", new exitClick());
                dialog.setIcon(R.drawable.icon1);
                dialog.create();
                dialog.show();
            }
        }
    }
    /*  普通对话框的“确定”按钮事件 */
    class okClick implements DialogInterface.OnClickListener
    {
        @Override
        public void onClick(DialogInterface dialog, int which)
        {
            dialog.cancel();//关闭对话框
        }
    }
    /*  输入对话框的“确定”按钮事件   */
    class loginClick implements  DialogInterface.OnClickListener
    {
        EditText txt;
        @Override
        public void onClick(DialogInterface dialog, int which)
        {
            txt = (EditText)login.findViewById(R.id.paswdEdit);//关联布局文件中的组件
            //取出输入编辑框的值与密码“admin”比较
            if((txt.getText().toString()).equals("admin"))
                Toast.makeText(getApplicationContext(),
                        "登录成功", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(),
                        "密码错误", Toast.LENGTH_SHORT).show();
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

