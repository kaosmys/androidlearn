package com.example.UItest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.example.AndroidProDiv.R;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-6-1
 * Time: 下午4:13
 * To change this template use File | Settings | File Templates.
 */
public class RelativeActivity extends Activity {
    int count = 1;
    static FrameLayout frameLayout = null;
    static ImageView imageView = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.framelayout);
        frameLayout = (FrameLayout) findViewById(R.id.framelayout);
    }

    public void btnClick1(View view) {
        Button button = (Button)view;
        //影藏现有的imgview
        imageView = (ImageView) frameLayout.findViewWithTag(String.valueOf(count));
        imageView.setVisibility(View.INVISIBLE);

        //显示下一个
        count++;
        if (count > 3) {
           count = 1;
        }

        //显示下一个图片
        imageView = (ImageView) frameLayout.findViewWithTag(String.valueOf(count));
        imageView.setVisibility(View.VISIBLE);
    }
}