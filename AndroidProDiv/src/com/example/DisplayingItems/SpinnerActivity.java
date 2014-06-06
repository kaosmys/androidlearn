package com.example.DisplayingItems;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.AndroidProDiv.R;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-6-6
 * Time: 下午7:20
 * To change this template use File | Settings | File Templates.
 */
public class SpinnerActivity extends Activity {
    Spinner spinner = null;
    String[] presidents = {"maozedong","dengxiaoping","huyaobang","jiangzeming","xijingping"};


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spinnermainactivity);
        //----spinner view
        spinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,
                presidents);
        spinner.setAdapter(adapter);

/*        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int index = spinner.getSelectedItemPosition();
                Toast.makeText(getBaseContext(),
                        "你已经选择了一个item: " + presidents[index] ,Toast.LENGTH_SHORT).show();
            }

            public void onNothingSelected(AdapterView<?> parent){

            }
        });*/
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int index = spinner.getSelectedItemPosition();
                Toast.makeText(getBaseContext(),
                        "你已经选择了一个item: " + presidents[index] ,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }
}