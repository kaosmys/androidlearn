package com.example.AccessHardware;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.ViewConfiguration;
import android.widget.Toast;
import com.example.AndroidProDiv.R;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-6-10
 * Time: 上午9:15
 * To change this template use File | Settings | File Templates.
 */
public class HardWareAccess extends Activity {

    //展示是否含有keyboard的信息
    private boolean isKeyboardPresent() {
        return getResources().getConfiguration().keyboard
                == Configuration.KEYBOARD_NOKEYS;
    }

    //展示是否含有实体键盘按键的信息
    private boolean isHwMenuButtonPresent(){
        return ViewConfiguration.get(this).hasPermanentMenuKey();
    }

    //展示是否有NFC功能的信息
    private boolean isNFCPresent() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_NFC);
    }

    //展示是否有前置摄像头信息
    private boolean isFrontPresent() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accessohterhardmain);

        Toast.makeText(this,"this dev's has keys?"
                + isKeyboardPresent(),Toast.LENGTH_SHORT).show();

        Toast.makeText(this,"this dev's has Menukeys?"
                + isHwMenuButtonPresent(),Toast.LENGTH_SHORT).show();

        Toast.makeText(this,"this dev's  has NFC?"
                + isNFCPresent(),Toast.LENGTH_SHORT).show();

        Toast.makeText(this,"this dev's  has frontCam?"
                + isFrontPresent(),Toast.LENGTH_SHORT).show();
    }
}