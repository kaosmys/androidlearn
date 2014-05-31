package com.example.usingbroadcastreceiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.AndroidProDiv.R;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-5-31
 * Time: 下午4:04
 * To change this template use File | Settings | File Templates.
 */
public class MainActivityOnbroadcast extends Activity {
    private Button button;
    private MyBroadcastReceiver myReceiver;
    private IntentFilter intentFilter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broadcastcast);
        button = (Button) this.findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent("My_Specific_action");
                i.putExtra("key","some value from intent");
                //从activity向broadcast传播的必要方法
                sendBroadcast(i);
            }
        });

        myReceiver = new MyBroadcastReceiver();
        intentFilter = new IntentFilter("My_Specific_action");

    }

    @Override
    public void onResume() {
        super.onResume();    //To change body of overridden methods use File | Settings | File Templates.
        //注册一个receiver
        registerReceiver(myReceiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();    //To change body of overridden methods use File | Settings | File Templates.
        //反注册receiver
        unregisterReceiver(myReceiver);
    }

    private class MyBroadcastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,
                        "Receiver broast in MyBroadCastReceiver"
                         + "value received:" + intent.getStringExtra("key"),
                    1000).show();
        }
    }
}