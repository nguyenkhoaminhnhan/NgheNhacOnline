package com.example.minhnhan.music.Activity;

import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.minhnhan.music.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by Minh Nhan on 11/23/2016.
 */

public class ScreenSlidePageFragment extends Fragment {
    public static final String ARG_PAGE = "page";

    public int pageID ;
    public String imgUrl;


    public ScreenSlidePageFragment(int position, String imgUrl) {
        this.imgUrl = imgUrl;

        pageID = position;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.swip_layout, container,
                false);
        ImageView imageView = (ImageView) rootView.findViewById(R.id.image_view);

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_stub)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.default_image).cacheInMemory(true)
                .cacheOnDisk(true).considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565).build();

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this.getContext()));
        imageLoader.displayImage(imgUrl, imageView, options, null);
        return rootView;
    }
}