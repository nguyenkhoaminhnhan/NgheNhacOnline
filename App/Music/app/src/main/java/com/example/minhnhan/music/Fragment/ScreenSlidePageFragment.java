package com.example.minhnhan.music.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.minhnhan.music.Activity.FullScreenPlayActivity;
import com.example.minhnhan.music.Model.Async.Data.DataManager;
import com.example.minhnhan.music.Model.Async.Data.MediaManager;
import com.example.minhnhan.music.Model.Song;
import com.example.minhnhan.music.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

/**
 * Created by Minh Nhan on 11/23/2016.
 */

public class ScreenSlidePageFragment extends Fragment {
    public static final String ARG_PAGE = "page";

    public int position;
    public Song data;


    public ScreenSlidePageFragment(int position, Song data) {
        this.data = data;

        this.position = position;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.swip_layout, container,
                false);
        ImageView songImage = (ImageView) rootView.findViewById(R.id.image_view);
        TextView songName = (TextView) rootView.findViewById(R.id.slide_song_name);
        songName.setText(data.name);
        TextView singerName = (TextView) rootView.findViewById(R.id.slide_singer_name);
        singerName.setText(data.singer);

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_stub)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.default_image).cacheInMemory(true)
                .cacheOnDisk(true).considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565).build();

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this.getContext()));
        imageLoader.displayImage(data.getImagePath(), songImage, options, null);

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Song> list = DataManager.getInstance().getHomeDetail().getShowCase();
                MediaManager.getInstance().setPlayList(list);
                MediaManager.getInstance().setCurrentPlayID(position);
                MediaManager.getInstance().setPlayingSong(data);
                Intent i = new Intent(getActivity(), FullScreenPlayActivity.class);
                getActivity().startActivityForResult(i, 11);
            }
        });
        return rootView;
    }
}