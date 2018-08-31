package com.isysnext.feedapp;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.DownloadListener;
import com.androidnetworking.interfaces.DownloadProgressListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.isysnext.feedapp.adapter.CategoryAdapter;
import com.isysnext.feedapp.dto.CategoryData;
import com.isysnext.feedapp.dto.TabDTO;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.ArrayList;

public class FullViewFragment extends Fragment {
    private Context context;
    static Context Mcontext;
    Fragment fragment = null;
    FragmentManager fragmentManager;
    static FragmentManager fm;
    RecyclerView homeCategoryList;
    ArrayList<TabDTO.Category> listCategory;
    CategoryAdapter adapter;
    CategoryData data;
    private ImageView back_appointment;
    View view;
    ImageView imgFull, imageSave,imagebcack;
    String img, thumb;

    public static Fragment getInstance(Context Mcntx, FragmentManager FM) {
        Mcontext = Mcntx;
        fm = FM;
        Fragment frag = new FullViewFragment();
        return frag;

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.full_view_fragment, container, false);
        context = getActivity();
        imgFull = (ImageView) view.findViewById(R.id.img_full);
        imageSave = (ImageView) view.findViewById(R.id.image_save);
        imagebcack = (ImageView) view.findViewById(R.id.image_back);
        fragmentManager = getChildFragmentManager();
        android.support.v7.app.ActionBar actionBar = ((MainActivity) context).getSupportActionBar();
        actionBar.hide();
        actionBar.setDisplayHomeAsUpEnabled(true);
        Bundle bundle = getArguments();
        if (bundle != null) {
            img = bundle.getString("image");
            thumb = bundle.getString("thumb");
        }
        //imgFull.setImageResource((Integer) bundle.get("image"));


        try {
            Glide.with(getActivity()).load(img).apply(RequestOptions.placeholderOf(Drawable.createFromPath(thumb))).into(imgFull);

        } catch (Exception e) {

            // imgFull.setImageResource(Integer.parseInt(bundle.getString("image")));
        }

        imageSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "Download Start", Toast.LENGTH_SHORT).show();
                downloadImageToGallery(img, context);
            }
        });


        imagebcack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        return view;
    }
//for save image to storage of device
    public void downloadImageToGallery(String path, Context context) {
        String fileName = String.valueOf(System.currentTimeMillis()) + ".jpg";
        String filepath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                .getAbsolutePath();

        AndroidNetworking
                .download(URLDecoder.decode(path), filepath
                        , fileName)
                .build().setDownloadProgressListener(new DownloadProgressListener() {
            @Override
            public void onProgress(long bytesDownloaded, long totalBytes) {
            }
        })
                .startDownload(new DownloadListener() {
                    @Override
                    public void onDownloadComplete() {
                        // do anything after completion
                        Toast.makeText(getActivity(), "Download Completed.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        error.getCause().printStackTrace();
                        Toast.makeText(getActivity(), "Some error occurred during downloading..", Toast.LENGTH_SHORT).show();
                    }
                });
    }



}
