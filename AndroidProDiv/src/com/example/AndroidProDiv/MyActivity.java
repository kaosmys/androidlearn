package com.example.AndroidProDiv;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MyActivity extends Activity {

    private Button button;
    private Button button2;
    private Button buttonOnObj;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //link- to activity2
/*        Intent i = new Intent("com.example.AndroidProdi.MyActivity2");
        startActivity(i);*/

        //another way to start activity
/*        Intent i = new Intent();
        i.setAction("com.example.AndroidProdi.MyActivity2");
        startActivity(i);*/

        //call activity from outside

        //��֤���򲻻�ͻȻ������ʹ��startActivity��Intent.creatChooser()һ��ʹ��
/*        Intent i = new Intent("com.example.AndroidProdi.MyActivity2");
        startActivity(Intent.createChooser(i,"choose an app"));*/
        button = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            Intent i = new Intent(MyActivity.this,MyActivity2.class);

            @Override
            public void onClick(View v) {
                //ֱ���������
                i.putExtra("str1", "this is a string");
                i.putExtra("age1", 24);

                //����Bundle�������������
                Bundle bundle = new Bundle();
                bundle.putString("str2","this is the 2nd time to add data");
                bundle.putString("age2", "36");
                i.putExtras(bundle);

                startActivityForResult(i, 1);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MyActivity.this,MyActivity2.class);
                Bundle bundle = new Bundle();
                i.setData(Uri.parse("http://wwww.baidu.com"));
                startActivityForResult(i,1);
            }
        });

        buttonOnObj = (Button) findViewById(R.id.buttononobj);
        buttonOnObj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MyActivity.this,MyActivity2.class);
                MycustomClass mycustomClass = new MycustomClass();
                mycustomClass.setEmail("woshihudapq@163.com");
                mycustomClass.setName("peter");
                i.putExtra("MycustomClass",mycustomClass);
                startActivityForResult(i,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this,data.getExtras().getString("age3"),1000).show();
                Uri url = data.getData();
                Toast.makeText(this,url.toString(),1000).show();
            }
        }
    }
}
