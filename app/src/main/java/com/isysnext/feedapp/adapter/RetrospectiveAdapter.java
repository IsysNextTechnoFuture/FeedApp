package com.isysnext.feedapp.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.isysnext.feedapp.Home;
import com.isysnext.feedapp.R;
import com.isysnext.feedapp.dto.TabDTO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Anuved on 29/08/18.
 */
public class RetrospectiveAdapter extends BaseAdapter{
    //Declaration of variables
    ArrayList<TabDTO.Wallpaper> listWallpaper;
    public Context mContext;
    private ProgressDialog mProgressDialog;
    private AsyncTask mMyTask;
    String imageURL;


    public RetrospectiveAdapter(Context mContext, ArrayList<TabDTO.Wallpaper> listWallpaper) {
        this.listWallpaper = listWallpaper;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return listWallpaper.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        LayoutInflater inflater = LayoutInflater.from(mContext);

        v = inflater.inflate(R.layout.restropective_list_adapter_view, null);
        ImageView imageWallpaper = (ImageView) v.findViewById(R.id.image_wallpaper);
        ImageView imageSave = (ImageView) v.findViewById(R.id.image_save);
        imageSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Clicked", Toast.LENGTH_SHORT).show();
                 imageURL = listWallpaper.get(position).getUrl();
                        MyAsyncTask asyncTask = new MyAsyncTask();
                        asyncTask.execute();


            }
        });
        if (listWallpaper.get(position).getCategory().equals(Home.sStrCategoryCheck)) {
            if (listWallpaper.get(position).getUrl() != null || !listWallpaper.get(position).getUrl().equals("")) {
                Glide.with(mContext).load(listWallpaper.get(position).getUrl())
                        .apply(RequestOptions.placeholderOf(Drawable.createFromPath(listWallpaper.get(position).getThumbUrl()))).into(imageWallpaper);
                Log.e("Image--------", listWallpaper.get(position).getUrl());
            } else {
                Glide.with(mContext).load(listWallpaper.get(position).getThumbUrl())
                        .apply(RequestOptions.placeholderOf(Drawable.createFromPath(listWallpaper.get(position).getThumbUrl()))).into(imageWallpaper);
            }
        }


        return v;

    }

    class MyAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL(imageURL);
                //create the new connection
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                //set up some things on the connection
                urlConnection.setRequestMethod("GET");
                urlConnection.setDoOutput(true);
                //and connect!
                urlConnection.connect();
                //set the path where we want to save the file in this case, going to save it on the root directory of the sd card.
                File SDCardRoot = Environment.getExternalStorageDirectory();
                //create a new file, specifying the path, and the filename which we want to save the file as.
                File file = new File(SDCardRoot,"image.jpg");
                //this will be used to write the downloaded data into the file we created
                FileOutputStream fileOutput = new FileOutputStream(file);
                //this will be used in reading the data from the internet
                InputStream inputStream = urlConnection.getInputStream();
                //this is the total size of the file
                int totalSize = urlConnection.getContentLength();
                //variable to store total downloaded bytes
                int downloadedSize = 0;
                byte[] buffer = new byte[1024];
                int bufferLength = 0; //used to store a temporary size of the buffer
                //now, read through the input buffer and write the contents to the file
                while ( (bufferLength = inputStream.read(buffer)) > 0 ) {
                    //add the data in the buffer to the file in the file output stream (the file on the sd card
                    fileOutput.write(buffer, 0, bufferLength);
                    //add up the size so we know how much is downloaded
                    downloadedSize += bufferLength;
                    //this is where you would do something to report the prgress, like this maybe
                    //updateProgress(downloadedSize, totalSize);
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(mContext,"Image Downloaded to sd card",Toast.LENGTH_SHORT).show();
        }
    }
    }