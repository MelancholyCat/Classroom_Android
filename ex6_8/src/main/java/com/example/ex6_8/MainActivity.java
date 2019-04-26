package com.example.ex6_8;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button getBtn, postBtn;
    TextView txt;
    EditText editname, editpsd, editemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getBtn = (Button) findViewById(R.id.button_get);
        postBtn = (Button) findViewById(R.id.button_psot);
        editpsd = (EditText) findViewById(R.id.editText_ID);
        editname = (EditText) findViewById(R.id.editText_Name);
        editemail = (EditText) findViewById(R.id.editText_email);
        txt = (TextView) findViewById(R.id.textView_txt);

        // 设置线程策略
        setVersion();

        getBtn.setOnClickListener(new mClick());
        postBtn.setOnClickListener(new mClick());
    }


    class mClick implements View.OnClickListener {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader buffer = null;
        HttpURLConnection connGET = null;
        HttpURLConnection connPOST = null;

        @Override
        public void onClick(View v) {
            if (v == getBtn) {
                String name = editname.getText().toString();
                String psd = editpsd.getText().toString();
                String email = editemail.getText().toString();
                try {
                    String str = "http://58.199.89.161/test/play-get.php?psd=" +
                            psd + "&name=" + name + "&email=" + email;
                    URL url = new URL(str);
                    connGET = (HttpURLConnection) url.openConnection();
                    connGET.setConnectTimeout(5000);
                    connGET.setRequestMethod("GET");
                    if (connGET.getResponseCode() == 200) {

                        buffer = new BufferedReader(new InputStreamReader(connGET.getInputStream()));
                        for (String s = buffer.readLine(); s != null; s = buffer.readLine()) {
                            stringBuilder.append(s);
                        }
                        txt.setText(stringBuilder);
                        buffer.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    txt.setText("get 提交 err.....");
                }
            }
            if (v == postBtn) {
                String name = editname.getText().toString();
                String psd = editpsd.getText().toString();
                String email = editemail.getText().toString();
                try {
                    String str = "http://58.199.89.161/test/play-post.php";
                    URL url = new URL(str);
                    connPOST = (HttpURLConnection) url.openConnection();
                    connPOST.setConnectTimeout(5000);
                    connPOST.setRequestMethod("POST");

                    // 发送POST请求必须设置如下两行
                    connPOST.setDoOutput(true);
                    connPOST.setDoInput(true);

                    //----------发送数据--------
                    PrintWriter printWriter = new PrintWriter(connPOST.getOutputStream());
                    Map<String, Object> paramsMap = new HashMap<String, Object>();

                    paramsMap.put("name", name);
                    paramsMap.put("psd", psd);
                    paramsMap.put("email", email);

                    printWriter.write(paramsMap.toString()); // 发送请求参数
                    printWriter.flush();

                    //----------接收数据--------
                    buffer = new BufferedReader(new InputStreamReader(connPOST.getInputStream()));
                    for (String s = buffer.readLine(); s != null; s = buffer.readLine()) {
                        stringBuilder.append(s);
                    }
                    txt.setText(stringBuilder);
                    buffer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    txt.setText("response err.....");
                }
            }
        }

    }

    void setVersion() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog()
                .build());
         /*   StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects() //探测SQLite数据库操作
                    .penaltyLog() //打印logcat
                    .penaltyDeath()
                    .build());
                    */
    }

}
