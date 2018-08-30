package com.isysnext.feedapp;

import android.content.Context;
import android.databinding.BaseObservable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.isysnext.feedapp.adapter.FeedsAdapter;
import com.twitter.sdk.android.core.models.Tweet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Anuved on 28/08/18.
 */
public class TwitterFeedsViewModel extends BaseObservable {
    private FeedsAdapter adapter;
    String USER_NAME = "gizelethakral";
    int count = 40;


    public void initFeedList(RecyclerView recyclerView) {

        adapter = new FeedsAdapter(recyclerView.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext()
                , DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
        getAuhToken(recyclerView.getContext());
    }

    private void getAuhToken(final Context context) {
        String combined = MyApplication.CONSUMER_KEY + ":" + MyApplication.CONSUMER_SECRET;
        String base64Encoded = Base64.encodeToString(combined.getBytes(), Base64.NO_WRAP);
        AndroidNetworking.post("https://api.twitter.com/oauth2/token")
                .addHeaders("Authorization", "Basic " + base64Encoded)
                .addHeaders("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                .addBodyParameter("grant_type", "client_credentials")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            requestTwitterFeeds(response.getString("access_token"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(context, "Some error occurred!", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void requestTwitterFeeds(String accessToken) {


        AndroidNetworking.get("https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=" + USER_NAME + "&count=" + count)
                .addHeaders("Authorization", "Bearer " + accessToken)
                .addHeaders("Content-Type", "application/json")
                .build().getAsJSONArray(new JSONArrayRequestListener() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<Tweet> tweetList = new Gson().fromJson(response.toString(), new TypeToken<ArrayList<Tweet>>() {
                }.getType());
                adapter.addTweetList(tweetList);
            }

            @Override
            public void onError(ANError anError) {

            }
        });

    }
}