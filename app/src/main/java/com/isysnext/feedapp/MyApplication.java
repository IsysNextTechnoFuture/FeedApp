package com.isysnext.feedapp;

import android.app.Application;
import java.io.File;
import java.io.IOException;


public class MyApplication extends Application {
    public static String CONSUMER_KEY = "DzpqeqEuBsJWBNiiV7jzOZhZo";
    public static String CONSUMER_SECRET = "wDBcE003poYUu1LzhCXaihHiy1oVIUmDPWaxhCa0DqjlTfdY0P";

    @Override
    public void onCreate() {
        super.onCreate();
        File outputDir = getCacheDir(); // context being the Activity pointer
        try {
            File outputFile = File.createTempFile("wallpaper_sample", ".json", outputDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
