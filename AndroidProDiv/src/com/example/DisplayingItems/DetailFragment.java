package com.example.DisplayingItems;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.AndroidProDiv.R;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-6-6
 * Time: 下午8:51
 * To change this template use File | Settings | File Templates.
 */
public class DetailFragment extends Fragment{

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_detail,container,false);
        return view;
    }
    /*产生已经选取好的presidents数组元素*/
    public void setSelectedPresident(String name) {
        TextView textView = (TextView) getView().findViewById(R.id.txtSelectedPresident);
        textView.setText("you hava choosen " + name);
    }
}
