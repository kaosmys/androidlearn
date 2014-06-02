package com.example.UItest;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;
import com.example.AndroidProDiv.R;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-6-1
 * Time: ����10:41
 * To change this template use File | Settings | File Templates.
 */
public class ButtonActivity extends Activity {
    String[] presidents = {"maozhedong","hujingtao","jiangzeming","xijingping"};

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buttonmain);

        //ʵ��������button����
        Button bt1 = (Button) findViewById(R.id.button_test_1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),"button1 was clicked",1000).show();
            }
        });

        Button bt3= (Button) findViewById(R.id.button_test_3);
        bt3.setOnClickListener(btOnclickListener);

        Button bt4= (Button) findViewById(R.id.button_test_4);
        Button bt5= (Button) findViewById(R.id.button_test_5);

        //adapter��Ϊһ���ܹ���װ���ݵ�ģʽ���ǵĳ��򲻻�ֱ��ȥ�Ӵ����ݣ����������չ��
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,presidents);
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autotxt);
        //�����û���Ҫ����������ַ�
        textView.setThreshold(3);
        textView.setAdapter(adapter);


    }

    private View.OnClickListener btOnclickListener  = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getBaseContext(),"button was clicked",1000).show();
        }
    };

    public void onClick(View view) {
        //�˴���viewһ��Ҫʵ����
        Button bt = (Button) view;
        Toast.makeText(this,"button was clicked to open",1000).show();
    }

    public void onToggle(View view) {
        Button bt = (Button) view;
        Toast.makeText(this,"button was clicked to toggle",1000).show();
    }
}
