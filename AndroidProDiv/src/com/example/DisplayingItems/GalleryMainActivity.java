package com.example.DisplayingItems;

import android.app.Activity;
import android.os.Bundle;
import com.example.AndroidProDiv.R;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-6-6
 * Time: 下午7:51
 * To change this template use File | Settings | File Templates.
 */
public class GalleryMainActivity extends Activity {
    //-----要展示的图片ID-----------
    Integer[] imgIDs = {
            R.drawable.fav32,
            R.drawable.favadd32,
            R.drawable.favb32,
            R.drawable.file32,
            R.drawable.filelocked32,
            R.drawable.filesearch32,
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallerymainactivity);

        //-----Gallery组件是4.1以上设备才至此后----
       /* Galler gellery = findViewById(R.id.gallery1);*/
    }
}