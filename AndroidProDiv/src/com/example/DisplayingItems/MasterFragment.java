package com.example.DisplayingItems;

import android.R;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-6-7
 * Time: 上午11:21
 * To change this template use File | Settings | File Templates.
 */
public class MasterFragment extends ListFragment{
    String[] presidents = {"maozedong",
            "dengxiapping",
            "jiangzeming",
            "hujingtai",
            "xijingping"};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                R.layout.simple_expandable_list_item_1,
                presidents));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(getActivity(),
                "you have choosen a " + presidents[position]
                ,Toast.LENGTH_SHORT ).show();
        String selectedPresidents = presidents[position];

        DetailFragment detailFragment = (DetailFragment)getFragmentManager()
                .findFragmentByTag("com.example.DisplayingItems.DetailFragment");

        //如果detailFragment不在目前的activity中
        if (detailFragment != null && detailFragment.isInLayout()) {
            //detailFragment和masterFragment在同一个activity中
            detailFragment.setSelectedPresident(selectedPresidents);
        }else {
            //如果detailFragment在其自己的activity中
            Intent intent = new Intent(getActivity(),DetailActivity.class);
            intent.putExtra("presidents",selectedPresidents);
            startActivity(intent);
        }

    }
}
