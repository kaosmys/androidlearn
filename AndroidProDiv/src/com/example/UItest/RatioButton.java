package com.example.UItest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import com.example.AndroidProDiv.R;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-6-1
 * Time: ����11:41
 * To change this template use File | Settings | File Templates.
 */
public class RatioButton extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retiobutton);

        //layout�еĿؼ�ȫ��Ҫʵ�֣���Ȼ�����nullpointer�Ĵ���
        RadioGroup rg = (RadioGroup) findViewById(R.id.rdbgp1);
        // RadioGroup��Ҫ����radiobutton�ı仯
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb1 = (RadioButton)findViewById(R.id.rdb1);
                if (rb1.isChecked()) {
                    Toast.makeText(getBaseContext(),
                            "option 1 checked",1000).show();
                } else {
                    Toast.makeText(getBaseContext(),
                            "option 2 checked",1000).show();
                }
            }
        });

        //checkedBox����
        CheckBox cb1 = (CheckBox) findViewById(R.id.checkBox);
        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                   Toast.makeText(getBaseContext(),"checkbox has been checked",500).show();
                } else {
                    Toast.makeText(getBaseContext(),"checkbox has been unhecked",500).show();
                }
            }
        });
    }
}