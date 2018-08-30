package com.isysnext.feedapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.isysnext.feedapp.adapter.RetrospectiveAdapter;
import com.isysnext.feedapp.dto.TabDTO;
import com.isysnext.feedapp.utility.OnItemClickListener;
import com.isysnext.feedapp.utility.Utilities;

import java.util.ArrayList;

public class Retrospective extends Fragment  {
    static Context Mcontext;
    private Context context;
    FragmentManager fragmentManager;
    static FragmentManager fm;
    private ImageView back_appointment;
    private GridView recyclerView;
    View view;
    private RecyclerView retrospective_list;
    Fragment fragment = null;
    private Utilities utilities;
    ArrayList<TabDTO.Wallpaper> listWallpaper;
    RetrospectiveAdapter adapter;
    private LinearLayoutManager mLinearLayoutManager;

    public static Fragment getInstance(Context Mcntx, FragmentManager FM) {
        Mcontext = Mcntx;
        fm = FM;
        Fragment frag = new Retrospective();
        return frag;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.retrospective_fragment, container, false);
        context = getActivity();
        utilities = Utilities.getInstance(getActivity());
        listWallpaper = new ArrayList<>();
        recyclerView = (GridView) view.findViewById(R.id.list_retrospective);
        callVolleyWallpaperList();
        return view;
    }

    //Method for setting recycler view of wallpaper
    private void setUpRecyclerViewWallpaper() {
        //AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager(context, 500);
        //mLinearLayoutManager = new LinearLayoutManager(context);
        //recyclerView.setLayoutManager(mLinearLayoutManager);
        adapter = new RetrospectiveAdapter(context, listWallpaper);
        recyclerView.setAdapter(adapter);
    }

    //Method for calling volley web service of wallpaper list
    private void callVolleyWallpaperList() {
        if (!utilities.isNetworkAvailable())
            utilities.dialogOK(getActivity(), "", getResources().getString(R.string.network_error), getString(R.string.ok), false);
        else {
            String url = "https://raw.githubusercontent.com/uidhinc/DishaPatani/master/wallpapers.json";
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new com.android.volley.Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                if (response == null) {
                                    utilities.dialogOK(context, context.getResources().getString(R.string.whoops), context.getResources().getString(R.string.server_error),
                                            context.getString(R.string.ok), false);
                                } else {
                                    Gson gson = new Gson();
                                    TabDTO tabDTO = gson.fromJson(response, TabDTO.class);
                                    if (tabDTO.getCategories().size() > 0) {
                                        Log.e("ListBeforeRemove", "" + listWallpaper.size());
                                        listWallpaper.clear();
                                        for (int i = 0; i < tabDTO.getWallpapers().size(); i++) {
                                            if (tabDTO.getWallpapers().get(i).getCategory().equals(Home.sStrCategoryCheck))
                                                listWallpaper.add(tabDTO.getWallpapers().get(i));
                                        }
                                        Log.e("ListAfterRemove", "" + listWallpaper.size());
                                        setUpRecyclerViewWallpaper();
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            //Add the request to the RequestQueue.
            Volley.newRequestQueue(context).add(stringRequest);
        }
    }

    //Adapter click on list item
    private OnItemClickListener.OnItemClickCallback onItemClickCallback = new OnItemClickListener.OnItemClickCallback() {
        @Override
        public void onItemClicked(View view, int position) {
            switch (position) {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:
                    break;
            }
        }
    };
}
