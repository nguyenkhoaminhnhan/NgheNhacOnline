package com.example.minhnhan.music.Fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.minhnhan.music.Model.Async.Data.MediaManager;
import com.example.minhnhan.music.Model.Song;
import com.example.minhnhan.music.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by Minh Nhan on 11/23/2016.
 */

public class ImagePlayFragment extends Fragment {
    public static final String ARG_PAGE = "page";

    public Song data;
    private ImageView songImage;
    private ImageView animate;
    private DisplayImageOptions options;

    public ImagePlayFragment(Song data) {
        this.data = data;
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_stub)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.default_image).cacheInMemory(true)
                .cacheOnDisk(true).considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565).build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.image_play_body, container,
                false);
        songImage = (ImageView) rootView.findViewById(R.id.play_image);
        animate = (ImageView) rootView.findViewById(R.id.playing_animation);

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this.getContext()));
        imageLoader.displayImage(data.getImagePath(), songImage, options, null);
        return rootView;
    }

    public void update() {
        data = MediaManager.getInstance().getPlayingSong();
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this.getContext()));
        imageLoader.displayImage(data.getImagePath(), songImage, options, null);
    }
}