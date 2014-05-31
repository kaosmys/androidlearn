package com.example.IntentFilter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.example.AndroidProDiv.R;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-5-31
 * Time: ÏÂÎç10:07
 * To change this template use File | Settings | File Templates.
 */
public class MainActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_browser);

        Intent i = new Intent("com.example.IntentFilter.Mybrowser");
        i.setData(Uri.parse("http://www.baidu.com"));
        startActivity(i);
    }
}