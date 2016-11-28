package com.example.minhnhan.music.Utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by MinhNhan on 01/06/2016.
 */
public class Utils {

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        // if no network is available networkInfo will be null
        // otherwise check if we are connected
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }

    public static void AddHotSongLinearL(LinearLayout linearLayout, BaseAdapter baseAdapter, LinearLayout.LayoutParams params) {

        for (int i = 0; i < baseAdapter.getCount(); i++) {
            View item = baseAdapter.getView(i, null, null);
            item.setLayoutParams(params);
            linearLayout.addView(item);
        }
    }

    public static void AddSongListLinearL(LinearLayout linearLayout, BaseAdapter baseAdapter) {

        for (int i = 0; i < baseAdapter.getCount(); i++) {
            View item = baseAdapter.getView(i, null, null);
            linearLayout.addView(item);
        }
    }
}
