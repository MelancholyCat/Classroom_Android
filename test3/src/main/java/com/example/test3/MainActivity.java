package com.example.test3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Button btn1;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        btn1 = (Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(new mClick());
        img = (ImageView)findViewById(R.id.img);
    }

    class mClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this,TextActivity.class);
            startActivity(intent);
        }
    }

}
