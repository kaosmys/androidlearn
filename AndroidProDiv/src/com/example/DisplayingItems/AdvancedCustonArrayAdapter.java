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
 * Time: ����8:30
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

        //--��Ӧÿ�е�����Ϊ�˼��
        Log.d("CustomArrayAdapter",String.valueOf(position));
        //Ϊÿ�е�չʾ����
        if (view == null) {
            Log.d("CustomArrayAdapter","new");
            LayoutInflater layoutInflater = context.getLayoutInflater();
            view = layoutInflater.inflate(R.layout.ivrowlayout2,
                    null,true);

            //����һ��view����ʵ��
            viewContainer = new ViewContainer();

            //Ϊÿһ�е�viewsʵ��������
            viewContainer.imageView = (ImageView) view.findViewById(R.id.icon);
            viewContainer.txtTitle = (TextView) view.findViewById(R.id.txtPresidents);
            viewContainer.txtDec = (TextView) view.findViewById(R.id.txtDsc);

            //��ʵ����������view������ӵ�rowview�С�
            view.setTag(viewContainer);
        }else {
            //---����view(inflater)�Ѿ������������ˣ���˿���ѭ������
            Log.d("CustomArrayAdapter","recycling");
            //----���Ը�������ȥ�ҵ�֮ǰ�Ѿ���contaner���������������ͨ��findByID
            // ���Ǻܷ��ڴ��
            viewContainer = (ViewContainer) view.getTag();

        }
        //----�Զ����ÿ��content���Ը���position������
        viewContainer.txtTitle.setTag(presidents[position]);
        viewContainer.txtDec.setText(presidents[position]
                + "...add some description");
        viewContainer.imageView.setImageResource(imgIDs[position]);
        return view;
    }
}
