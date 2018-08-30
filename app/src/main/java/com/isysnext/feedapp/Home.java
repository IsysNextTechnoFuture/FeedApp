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
import android.widget.ImageView;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.isysnext.feedapp.adapter.CategoryAdapter;
import com.isysnext.feedapp.dto.CategoryData;
import com.isysnext.feedapp.dto.TabDTO;
import com.isysnext.feedapp.utility.OnItemClickListener;
import com.isysnext.feedapp.utility.Utilities;
import java.util.ArrayList;

/**
 * Created by Anuved on 29/08/18.
 */
public class Home extends Fragment  {

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
    View view,viewCategory;
    Fragment fragment = null;
    private Utilities utilities;
    public static String sStrCategoryCheck="";

    public static Fragment getInstance(Context Mcntx, FragmentManager FM) {
        Mcontext = Mcntx;
        fm = FM;
        Fragment frag = new Home();
        return frag;

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);
        context = getActivity();
        utilities = Utilities.getInstance(getActivity());
        listCategory = new ArrayList<>();
        fragmentManager = getChildFragmentManager();
       // viewCategory = (View) view.findViewById(R.id.view_category);
        homeCategoryList = (RecyclerView) view.findViewById(R.id.home_category_list);
        callVolleyCategoryList();
        showFragment();
        return view;
    }

    //Method for setting recycler view of category
    private void setUpRecyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        homeCategoryList.setLayoutManager(linearLayoutManager);
        adapter = new CategoryAdapter(context, listCategory, onItemClickCallback);
        homeCategoryList.setAdapter(adapter);
    }

    //Method for calling volley web service of category list
    private void callVolleyCategoryList() {
        if (!utilities.isNetworkAvailable())
            utilities.dialogOK(getActivity(), "", getResources().getString(R.string.network_error), getString(R.string.ok), false);
        else {
            String url = "https://raw.githubusercontent.com/uidhinc/DishaPatani/master/wallpapers.json";
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new com.android.volley.Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                Log.e("Response=====",response);
                                if (response == null) {
                                    utilities.dialogOK(context, context.getResources().getString(R.string.whoops), context.getResources().getString(R.string.server_error),
                                            context.getString(R.string.ok), false);
                                } else {
                                    Gson gson = new Gson();
                                    TabDTO tabDTO = gson.fromJson(response, TabDTO.class);
                                    if (tabDTO.getCategories().size() > 0) {
                                        listCategory.addAll(tabDTO.getCategories());
                                        sStrCategoryCheck=listCategory.get(0).getName();
                                        setUpRecyclerViewCategory();
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
                    sStrCategoryCheck=listCategory.get(position).getName();
                    showFragment();
                    break;
                case 1:
                    sStrCategoryCheck=listCategory.get(position).getName();
                    showFragment();
                    break;
                case 2:
                    sStrCategoryCheck=listCategory.get(position).getName();
                    showFragment();
                    break;
                case 3:
                    sStrCategoryCheck=listCategory.get(position).getName();
                    showFragment();
                    break;
                default:
                    break;
            }
        }
    };

    //Method for showing fragment
    private void showFragment() {
        fragmentManager = getActivity().getSupportFragmentManager();
        fragment = Retrospective.getInstance(getActivity(), fragmentManager);
        fragmentManager.beginTransaction().replace(R.id.home_container, fragment, "Retrospective").commit();
    }

    }
