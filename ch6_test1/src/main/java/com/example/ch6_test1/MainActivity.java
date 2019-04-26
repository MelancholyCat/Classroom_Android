package com.example.ch6_test1;

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
    Button postBtn, getBtn;
    TextView txt;
    EditText editname, editpsd, ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editname = (EditText) findViewById(R.id.ed_username);
        editpsd = (EditText) findViewById(R.id.ed_password);
        ip = (EditText) findViewById(R.id.ip);
        getBtn = (Button) findViewById(R.id.btn_GET);
        postBtn = (Button) findViewById(R.id.btn_POST);
        txt = (TextView) findViewById(R.id.tv_result);

        // 设置线程策略
        setVersion();

        postBtn.setOnClickListener(new mClick());
        getBtn.setOnClickListener(new mClick());
    }

    void setVersion() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects() //探测SQLite数据库操作
                .penaltyLog() //打印logcat
                .penaltyDeath()
                .build());

    }

    class mClick implements View.OnClickListener {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader buffer = null;
        HttpURLConnection connGET = null;
        HttpURLConnection connPOST = null;
        String result;

        @Override
        public void onClick(View v) {

            String name = editname.getText().toString();
            String psd = editpsd.getText().toString();
            if (v == getBtn) {
                try {
                    String str = "http://" + ip.getText().toString() + ":8080/Android_ch6_test1_war_exploded/LoginServlet?password=" +
                            psd + "&username=" + name;
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
                    txt.setText("get 提交 err....." + e.toString());
                }
            }
            if (v == postBtn) {

                String str = "http://" + ip.getText().toString() + ":8080/Android_ch6_test1_war_exploded/LoginServlet";
                String param = "username=" + name + "&" + "password=" + psd; //设置参数
                try {
                    URL url = new URL(str);
                    connPOST = (HttpURLConnection) url.openConnection();
                    connPOST.setConnectTimeout(5000);//设置超时的时间，即5s
                    connPOST.setRequestMethod("POST");//设置以Post方式提交数据
                    connPOST.setUseCaches(false);//使用Post方式不能使用缓存

                    // 发送POST请求必须设置如下两行
                    connPOST.setDoOutput(true);
                    connPOST.setDoInput(true);

                    //----------发送数据--------
                    PrintWriter printWriter = new PrintWriter(connPOST.getOutputStream(),true);
                    Map<String, Object> paramsMap = new HashMap<String, Object>();

                    paramsMap.put("username", name);
                    paramsMap.put("password", psd);

                    printWriter.write(param); // 发送请求参数  paramsMap.toString()
                    printWriter.flush();

                    //----------接收数据--------
                    buffer = new BufferedReader(new InputStreamReader(connPOST.getInputStream()));
                    for (String s = buffer.readLine(); s != null; s = buffer.readLine()) {
                        stringBuilder.append(s);
                    }
                    txt.setText(stringBuilder+"/n"+param+"/t"+paramsMap.toString());
                    buffer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    txt.setText("response err....." + e.toString());
                }
            }
        }
    }
}

