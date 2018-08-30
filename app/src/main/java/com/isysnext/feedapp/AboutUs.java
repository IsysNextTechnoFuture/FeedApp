package com.isysnext.feedapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.isysnext.feedapp.adapter.CategoryAdapter;
import com.isysnext.feedapp.dto.CategoryData;
import com.isysnext.feedapp.dto.TabDTO;
import com.isysnext.feedapp.utility.Utilities;
import java.util.ArrayList;

/**
 * Created by Anuved on 29/08/18.
 */
public class AboutUs  extends Fragment {
    //Declaration of variables
    private View parentView;
    private Context context;
    static Context Mcontext;
    FragmentManager fragmentManager;
    static android.support.v4.app.FragmentManager fm;
    RecyclerView homeCategoryList;
    ArrayList<TabDTO.Category> listCategory;
    CategoryAdapter adapter;
    CategoryData data;
    private ImageView back_appointment;
    View view, viewCategory;
    Fragment fragment = null;
    private Utilities utilities;
    public static String sStrCategoryCheck = "";

    public static Fragment getInstance(Context Mcntx, FragmentManager FM) {
        Mcontext = Mcntx;
        fm = FM;
        Fragment frag = new AboutUs();
        return frag;

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.about_us, container, false);
       ;
        return view;
    }

}