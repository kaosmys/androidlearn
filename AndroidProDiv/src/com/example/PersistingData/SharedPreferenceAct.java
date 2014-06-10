package com.example.PersistingData;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;
import com.example.AndroidProDiv.R;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-6-10
 * Time: 上午11:06
 * To change this template use File | Settings | File Templates.
 */
public class SharedPreferenceAct extends Activity {
    SharedPreferences preferences;
    String prefName = "MyPfres";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharedprefercemain);

        //创建一个shared preferences实例
        preferences = getSharedPreferences(prefName,MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        //在editor中增加一些键值对
        editor.putFloat("temperature",30.5f);
        editor.putBoolean("authentic",true);
        editor.putInt("age",25);

        editor.commit();

        readPrefValues();
    }

    private void readPrefValues() {
        preferences = getSharedPreferences(prefName,MODE_PRIVATE);
        float temp = preferences.getFloat("temperature",30.5f);
        boolean authentic = preferences.getBoolean("authentic",true);
        int age = preferences.getInt("age",25);

        Toast.makeText(this,"the temp is" + temp,Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"the authentic is" + authentic,Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"the age is" + age,Toast.LENGTH_SHORT).show();
    }
}