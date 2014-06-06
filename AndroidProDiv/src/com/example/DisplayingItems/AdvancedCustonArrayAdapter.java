package com.example.DisplayingItems;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.AndroidProDiv.R;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-6-5
 * Time: 下午8:30
 * To change this template use File | Settings | File Templates.
 */
public class AdvancedCustonArrayAdapter extends ArrayAdapter<String>{
    private final Activity context;
    private final String[] presidents;
    private final Integer[] imgIDs;


    public AdvancedCustonArrayAdapter(Activity context, String[] presidents, Integer[] imgIDs) {
        super(context, R.layout.ivrowlayout2,presidents);
        this.context = context;
        this.presidents = presidents;
        this.imgIDs = imgIDs;
    }

    static class ViewContainer {
        public ImageView imageView;
        public TextView txtTitle;
        public TextView txtDec;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewContainer viewContainer;
        View view = convertView;

        //--答应每行的行数为了检测
        Log.d("CustomArrayAdapter",String.valueOf(position));
        //为每行的展示行数
        if (view == null) {
            Log.d("CustomArrayAdapter","new");
            LayoutInflater layoutInflater = context.getLayoutInflater();
            view = layoutInflater.inflate(R.layout.ivrowlayout2,
                    null,true);

            //创造一个view容器实例
            viewContainer = new ViewContainer();

            //为每一行的views实例化对象
            viewContainer.imageView = (ImageView) view.findViewById(R.id.icon);
            viewContainer.txtTitle = (TextView) view.findViewById(R.id.txtPresidents);
            viewContainer.txtDec = (TextView) view.findViewById(R.id.txtDsc);

            //将实例化对象后的view容器添加到rowview中。
            view.setTag(viewContainer);
        }else {
            //---由于view(inflater)已经被创建出来了，因此可以循环利用
            Log.d("CustomArrayAdapter","recycling");
            //----可以根据索引去找到之前已经绑定contaner的容器，如果都是通过findByID
            // 将是很费内存的
            viewContainer = (ViewContainer) view.getTag();

        }
        //----自定义的每行content可以根据position来排序
        viewContainer.txtTitle.setTag(presidents[position]);
        viewContainer.txtDec.setText(presidents[position]
                + "...add some description");
        viewContainer.imageView.setImageResource(imgIDs[position]);
        return view;
    }
}
