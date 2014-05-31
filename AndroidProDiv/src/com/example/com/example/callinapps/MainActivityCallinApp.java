package com.example.com.example.callinapps;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.example.AndroidProDiv.R;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-5-31
 * Time: 下午8:59
 * To change this template use File | Settings | File Templates.
 */
public class MainActivityCallinApp extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.callinbuiltin);

        /*Intent i = new Intent(Intent.ACTION_VIEW);*/
        //调用google地图来展示数据
       /* i.setData(Uri.parse("geo:37.827500,-122.481670"));
        startActivity(i);*/

        //登录到google play
/*        i.setData(Uri.parse("market://details?id=com.zinio.moblie.android.reader"));
        startActivity(i);*/

        //发送e-mail
/*        Intent i = new Intent(Intent.ACTION_SEND);
        i.setData(Uri.parse("mailto:"));
        String[] to = {"woshihudapq@163.com","woshitshpq@163.com"};
        String[] cc = {"306111052@qq,com","1250321541@qq.com"};
        i.putExtra(Intent.EXTRA_EMAIL,to);
        i.putExtra(Intent.EXTRA_CC,cc);
        i.putExtra(Intent.EXTRA_SUBJECT,"Subject here...");
        i.putExtra(Intent.EXTRA_TEXT,"Message here...");
        i.setType("message/rfc822");
        startActivity(Intent.createChooser(i,"Email"));*/

        //发送数据至其他应用
/*        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");                       //设置MIME数据类型
        i.putExtra(Intent.EXTRA_SUBJECT,"subject...");
        i.putExtra(Intent.EXTRA_TEXT,"text...");
        startActivity(Intent.createChooser(i,"App that can reapond to this"));*/

        // 发送二进制内容之其他程序
        Uri uri2Image = Uri.parse("android.resource://" +
                "com.example.com.example.callinapps"
                + Integer.toString(R.drawable.penguins));
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("image/jpg");
        i.putExtra(Intent.EXTRA_STREAM,uri2Image);
        i.putExtra(Intent.EXTRA_TEXT,"text....");
        startActivity(Intent.createChooser(i,"app that can response to this"));
    }
}