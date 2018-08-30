package com.isysnext.feedapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.isysnext.feedapp.R;
import com.isysnext.feedapp.dto.DrawerDataModel;
import java.util.ArrayList;

/**
 * Created by Anuved on 28/08/18.
 */
public class DrawerAdpter extends BaseAdapter {
    //Declaration of variables
    private ArrayList<DrawerDataModel> arrayList;
    private Context context;

    //Constructor
    public DrawerAdpter(Context context, ArrayList<DrawerDataModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = LayoutInflater.from(context);
        v = inflater.inflate(R.layout.tray_list_item,null);
        ImageView drawr_list_icon = (ImageView) v.findViewById(R.id.drawr_list_icon);
        drawr_list_icon.setImageResource(arrayList.get(position).getDrawerImage());

        return v;
    }
}
