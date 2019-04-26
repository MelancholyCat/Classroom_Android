package com.example.test4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        调用父类方法来加入系统菜单
        super.onCreateOptionsMenu(menu);
//        添加菜单项
        menu.add(1,1,1,"菜单项1");
        menu.add(1,2,2,"菜单项2");
        menu.add(1,3,3,"菜单项3");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String title = "选择了"+item.getTitle().toString();
        Intent intent = new Intent();
        switch (item.getItemId()){
            case 1:
                intent = new Intent(MainActivity.this,FirstActivity.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(MainActivity.this,ThirdActivity.class);
                startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
