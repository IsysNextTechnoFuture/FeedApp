package com.isysnext.feedapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Twitter extends Fragment {

    View view;
    Fragment fragment = null;
    static Context Mcontext;
    RecyclerView recyclerView;
    static android.support.v4.app.FragmentManager fm;

    public static Fragment getInstance(Context Mcntx, FragmentManager FM) {
        Mcontext = Mcntx;
        fm = FM;
        Fragment frag = new Twitter();
        return frag;

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.twitter, container, false);
        return view;
    }
}
