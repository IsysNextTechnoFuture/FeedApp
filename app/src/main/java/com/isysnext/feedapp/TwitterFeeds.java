package com.isysnext.feedapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.isysnext.feedapp.databinding.FragmentTwitterFeedsBinding;


public class TwitterFeeds extends Fragment {

    public TwitterFeeds() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentTwitterFeedsBinding binding;
        binding = FragmentTwitterFeedsBinding.inflate(inflater,
                container, false);
        TwitterFeedsViewModel vm = new TwitterFeedsViewModel();
        vm.initFeedList(binding.feedList);
        return binding.getRoot();
    }

}
