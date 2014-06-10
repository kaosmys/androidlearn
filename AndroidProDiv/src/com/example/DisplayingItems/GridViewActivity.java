package com.example.DisplayingItems;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.AndroidProDiv.R;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-6-6
 * Time: ����8:05
 * To change this template use File | Settings | File Templates.
 */
public class GridViewActivity extends Activity {
    //---��Ҫչʾ��ͼƬ--
    Integer[] imgIDs = {
            R.drawable.fav32,
            R.drawable.filesearch32,
            R.drawable.filelocked32,
            R.drawable.file32,
            R.drawable.favb32,
            R.drawable.favadd32,
            R.drawable.find32,
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridviewmain);

        GridView gridView = (GridView) findViewById(R.id.gridView1);
        ImageAdapter adapter = new ImageAdapter(getBaseContext());
        gridView.setAdapter(adapter);

        gridView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(),
                        "pic" + (position + 1) + "selected",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });

    }


    private class ImageAdapter extends BaseAdapter {
        private Context context;

        public ImageAdapter(Context c) {
            this.context = c;
        }

        @Override
        public int getCount() {
            return imgIDs.length;
        }
        //����item
        @Override
        public Object getItem(int position) {
            return position;
        }

        //����item��id
        @Override
        public long getItemId(int position) {
            return position;
        }
        //����imageview��view
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
             ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(context);
                imageView.setLayoutParams(new GridView.LayoutParams(85,85));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                //����ÿ��imageview��֮��ľ���
                imageView.setPadding(5,5,5,5);
            }else {
                imageView = (ImageView) convertView;
            }
            imageView.setImageResource(imgIDs[position]);
            return imageView;
        }
    }
}