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
 * Time: ����8:59
 * To change this template use File | Settings | File Templates.
 */
public class MainActivityCallinApp extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.callinbuiltin);

        /*Intent i = new Intent(Intent.ACTION_VIEW);*/
        //����google��ͼ��չʾ����
       /* i.setData(Uri.parse("geo:37.827500,-122.481670"));
        startActivity(i);*/

        //��¼��google play
/*        i.setData(Uri.parse("market://details?id=com.zinio.moblie.android.reader"));
        startActivity(i);*/

        //����e-mail
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

        //��������������Ӧ��
/*        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");                       //����MIME��������
        i.putExtra(Intent.EXTRA_SUBJECT,"subject...");
        i.putExtra(Intent.EXTRA_TEXT,"text...");
        startActivity(Intent.createChooser(i,"App that can reapond to this"));*/

        // ���Ͷ���������֮��������
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