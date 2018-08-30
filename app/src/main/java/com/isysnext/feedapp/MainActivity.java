package com.isysnext.feedapp;

import android.Manifest;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.isysnext.feedapp.adapter.DrawerAdpter;
import com.isysnext.feedapp.dto.DrawerDataModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Fragment fragment = null;
    FragmentManager fragmentManager;
    ListView trayListMenu;
    ArrayList<DrawerDataModel> arrayList;
    DrawerDataModel data;
    Toolbar toolbar;
    TextView toolbar_text;
    private static final int EXTERNAL_STORAGE_PERMISSION_CONSTANT = 100;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        // Hide the Status Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_PERMISSION_CONSTANT);
        }
        trayListMenu = (ListView) findViewById(R.id.tray_list_menu);
        arrayList = new ArrayList<>();

        toolbar = (Toolbar) findViewById(R.id.toolbar_splash);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar_text = (TextView) findViewById(R.id.toolbar_title);
        toolbar_text.setText("Wallpaper");

        fragmentManager = getSupportFragmentManager();
        fragment = Home.getInstance(this, fragmentManager);
        fragmentManager.beginTransaction().replace(R.id.main_container, fragment, "Home").commit();
        data = new DrawerDataModel(R.mipmap.disha_patani);
        arrayList.add(data);

        data = new DrawerDataModel(R.mipmap.ic_wall);
        arrayList.add(data);

        data = new DrawerDataModel(R.mipmap.ic_tw);
        arrayList.add(data);

       /* data = new DrawerDataModel(R.mipmap.ic_insta);
        arrayList.add(data);*/

        data = new DrawerDataModel(R.mipmap.ic_about);
        arrayList.add(data);

        trayListMenu.setAdapter(new DrawerAdpter(this,arrayList));
        trayListMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==1)
                {
                    fragment = Home.getInstance(MainActivity.this, fragmentManager);
                    fragmentManager.beginTransaction().replace(R.id.main_container, fragment, "Home").commit();
                    toolbar_text.setText("Wallpaper");
                }
                if (position==2)
                {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_container, new TwitterFeeds()).addToBackStack("").commit();
                    toolbar_text.setText("Twitter");
                }
                if (position==3)
                {
                    fragment = AboutUs.getInstance(MainActivity.this, fragmentManager);
                    fragmentManager.beginTransaction().replace(R.id.main_container, fragment, "AboutsUs").commit();
                    toolbar_text.setText("About Us");
                }
            }
        });

    }
}
