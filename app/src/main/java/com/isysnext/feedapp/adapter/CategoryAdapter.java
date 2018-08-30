package com.isysnext.feedapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.isysnext.feedapp.R;
import com.isysnext.feedapp.dto.TabDTO;
import com.isysnext.feedapp.utility.OnItemClickListener;
import java.util.ArrayList;

/**
 * Created by Anuved on 28/08/18.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>  {

    //Declaration of variables
    private ArrayList<TabDTO.Category> arrayList;
    private Context context;
    public static View view_category;
    private OnItemClickListener.OnItemClickCallback onItemClickCallback;

    //Constructor
    public CategoryAdapter(Context context, ArrayList<TabDTO.Category> arrayList,
                           OnItemClickListener.OnItemClickCallback onItemClickCallback) {
        this.arrayList = arrayList;
        this.context = context;
        this.onItemClickCallback = onItemClickCallback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout = -1;
        switch (viewType) {
            case 0:
                layout = R.layout.category_list_adpter;
                break;
        }
        //Inflate view on adapter
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        ViewHolder holder = new ViewHolder(v);
        v.setTag(holder);
        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tvCategoryname.setText(arrayList.get(position).getName());
        //Click Event
        holder.llMain.setOnClickListener(new OnItemClickListener(position, onItemClickCallback));
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    //Class for holding view
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCategoryname;
        LinearLayout llMain;

        public ViewHolder(View view) {
            super(view);
            tvCategoryname = (TextView) view.findViewById(R.id.tv_category_name);
            llMain = (LinearLayout) view.findViewById(R.id.ll_main);
            view_category = (View) view.findViewById(R.id.view_category);

        }
    }
}