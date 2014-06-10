package com.example.MakePhoneCalls;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.example.AndroidProDiv.R;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-6-7
 * Time: ÏÂÎç2:16
 * To change this template use File | Settings | File Templates.
 */
public class MainActivity411_makingphonecall extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.makingphonecallmain);

        String num = "13048848020";
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+" + num));
        startActivity(intent);

    }
}