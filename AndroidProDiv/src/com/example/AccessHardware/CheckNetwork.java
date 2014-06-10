package com.example.AccessHardware;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;
import com.example.AndroidProDiv.R;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-6-10
 * Time: ÉÏÎç9:41
 * To change this template use File | Settings | File Templates.
 */
public class CheckNetwork extends Activity {
    ConnectivityManager cm;
    NetworkInfo wifiInfo,mobileInfo;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checknetworkmain);

        cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        mobileInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (wifiInfo.isConnected()) {
            Toast.makeText(this,"wifi is connected",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"wifi is not connected",Toast.LENGTH_SHORT).show();
        }

        if (mobileInfo.isConnected()) {
            Toast.makeText(this,"moblie connect is connected",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"moblie connect is not connected",Toast.LENGTH_SHORT).show();
        }

    }
}