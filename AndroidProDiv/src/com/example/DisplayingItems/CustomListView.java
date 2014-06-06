package com.example.DisplayingItems;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import com.example.AndroidProDiv.R;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-6-5
 * Time: 下午8:24
 * To change this template use File | Settings | File Templates.
 */
public class CustomListView extends ListActivity {
    String[] presidents = {"maozedong"
            , "hujingtao"
            ,"dengxiaoping"
            ,"xijinping"};

    Integer[] imagIDs = {
            R.drawable.buttonpause,
            R.drawable.buttonplay,
            R.drawable.buttonprevious,
            R.drawable.penguins};




    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cutomlistactivity);

        //----使用自定义的layout
        AdvancedCustonArrayAdapter adapter = new AdvancedCustonArrayAdapter(
                this,presidents,imagIDs);

        setListAdapter(adapter);
    }
}