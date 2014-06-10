package com.example.DisplayingItems;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;
import com.example.AndroidProDiv.R;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-6-7
 * Time: ����11:47
 * To change this template use File | Settings | File Templates.
 */
public class DetailActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //����û�ת������ģʽ�������ٴ�activity
        if (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE)
        {
            finish();
            return;
        }
        //ʹ�ô�masterFragment����������
        String name = getIntent().getStringExtra("presidents");
        TextView textView = (TextView) findViewById(R.id.txtSelectedPresident);
        textView.setText("you have selected " + name);
    }
}