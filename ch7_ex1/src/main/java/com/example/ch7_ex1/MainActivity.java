package com.example.ch7_ex1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText txt1, txt2, txt3;
    Button jsonBtn, arrayBtn;
    /*  设有Json数据
     JSONArray jdata = [{"sid":1001, "name":"张大山"},
                        {"sid":1002, "name":"李小丽"} ];
     */
    JSONObject jid, jname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt1 = (EditText) findViewById(R.id.editText);
        txt2 = (EditText) findViewById(R.id.editText2);
        txt3 = (EditText) findViewById(R.id.editText3);
        jsonBtn = (Button) findViewById(R.id.button);
        arrayBtn = (Button) findViewById(R.id.button2);
        jsonBtn.setOnClickListener(new mClick());
        arrayBtn.setOnClickListener(new mClick());

    }

    void setJsonData() {
        try {
            jid = new JSONObject();
            jname = new JSONObject();
            jid.put("sid", 1001);
            jname.put("name", "张大山");
            /*jid.put("sid", txt1.getText());
            jname.put("name", txt2.getText());*/
            String sid = jid.getString("sid");
            txt1.setText(sid);
            String sname = jname.getString("name");
            txt2.setText(sname);
        } catch (JSONException e) {}
    }

    void setArrayData() {
        try {
            JSONArray jdata = new JSONArray();
            JSONObject p1 = new JSONObject();
            JSONObject p2 = new JSONObject();
            p1.put("sid", 1001).put("name", "张大山");
            p2.put("sid", 1002).put("name", "李小丽");
            jdata.put(p1);
            jdata.put(p2);
            String sid, sname;

            int length = jdata.length();
            for (int i = 0; i < length; i++) {    //遍历JSONArray
                JSONObject jsonObject = jdata.getJSONObject(i);
                sid = jsonObject.getString("sid") + ":";
                sname = jsonObject.getString("name") + "\n";
                txt3.append(sid + sname);
            }
        } catch (JSONException e) {}
    }

    public class mClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (v == jsonBtn) setJsonData();
            else if (v == arrayBtn) setArrayData();
        }
    }

}
