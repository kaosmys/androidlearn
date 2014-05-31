package com.example.AndroidProDiv;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-5-31
 * Time: 上午11:36
 * To change this template use File | Settings | File Templates.
 */
public class MyActivity2 extends Activity {
    private Button button;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seconact);
        Intent i = this.getIntent();
        Bundle bundle = i.getExtras();
        Toast.makeText(this,bundle.getString("str1"),1000).show();
        Toast.makeText(this,bundle.getString("age2"),1000).show();

        MycustomClass mycustomClass = (MycustomClass) getIntent().getSerializableExtra("MycustomClass");
        Toast.makeText(this,mycustomClass.getName(),1000).show();
        Toast.makeText(this,mycustomClass.getEmail(),1000).show();


        button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(MyActivity2.this,MyActivity.class);
                i2.putExtra("age3",45);
                i2.setData(Uri.parse("http://www.baidu.com"));
                setResult(RESULT_OK,i2);
                //一定要加finish（）作为返回值结果的方法
                finish();
            }
        });
    }
}