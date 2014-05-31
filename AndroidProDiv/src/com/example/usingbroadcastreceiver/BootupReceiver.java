package com.example.usingbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-5-31
 * Time: 下午8:44
 * To change this template use File | Settings | File Templates.
 */
public class BootupReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"app_started",1000).show();
        //自动启动app在手机开机时
        Intent i = new Intent(context,MainActivityOnbroadcast.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
