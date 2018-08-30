package com.isysnext.feedapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class IntroFragment extends Fragment {
    private ImageView introImage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_intro, container, false);
        introImage = view.findViewById(R.id.introImage);
        setImage();
        return view;
    }

    private void setImage() {
        Bundle bundle = getArguments();
        int val = bundle.getInt("code");
        switch (val) {
            case 0:
                Glide.with(getContext()).load(R.drawable.one).into(introImage);
                break;
            case 1:
                Glide.with(getContext()).load(R.drawable.two).into(introImage);
                break;
            default:
                Glide.with(getContext()).load(R.drawable.three).into(introImage);
                break;
        }
    }}
