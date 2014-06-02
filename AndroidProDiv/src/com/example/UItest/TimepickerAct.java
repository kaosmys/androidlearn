package com.example.UItest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.widget.TimePicker;
import com.example.AndroidProDiv.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-6-1
 * Time: ÏÂÎç3:20
 * To change this template use File | Settings | File Templates.
 */
public class TimepickerAct extends Activity {
    android.widget.TimePicker timePicker = null;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timepickup);

        timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);

        Button button = (Button) findViewById(R.id.btnset);

    }
     public void btnClick(View view) {
         Button button = (Button)view;
         final NumberFormat format = new DecimalFormat("00");
         button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Toast.makeText(getBaseContext(),"Time has been selected!"
                         + timePicker.getCurrentHour() +":"
                         + format.format(timePicker.getCurrentMinute()),1000).show();
             }
         });
     }
}