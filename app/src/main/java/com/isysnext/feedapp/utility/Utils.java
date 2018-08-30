package com.isysnext.feedapp.utility;

import android.app.Dialog;
import android.content.Context;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.DownloadListener;
import com.androidnetworking.interfaces.DownloadProgressListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.isysnext.feedapp.R;

import java.net.URLDecoder;

public class Utils {
    public static void showFullImage(final Context context, String path) {
        View view = LayoutInflater.from(context).inflate(R.layout.full_size_image, null);
        Dialog dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.setContentView(view);
        ImageView imageView = view.findViewById(R.id.fullImage);
        Button btnSave = view.findViewById(R.id.btnSave);
        Glide.with(context).load(path).apply(new RequestOptions().placeholder(R.drawable.ic_launcher_background)).into(imageView);
        dialog.show();
    }

    public static void downloadImageToGallery(String path, final Context context) {
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
                        Toast.makeText(context, "Download finished.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        error.getCause().printStackTrace();
                        Toast.makeText(context, "Some error occurred during downloading..", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
