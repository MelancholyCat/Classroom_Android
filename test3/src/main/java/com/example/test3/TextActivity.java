package com.example.test3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TextActivity extends AppCompatActivity{

    private Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_layout);
        init();
    }

    private void init(){
        btn2 = (Button)findViewById(R.id.btn2);
        btn2.setOnClickListener(new mClick());
    }

    class mClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(TextActivity.this,MainActivity.class);
            startActivity(intent);
        }
    }

}
