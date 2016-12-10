package com.example.minhnhan.music.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.minhnhan.music.Adapter.PlayListAdapter;
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

public class PlayListFragment extends Fragment {
    public static final String ARG_PAGE = "page";

    public ArrayList<Song> data;


    public PlayListFragment(ArrayList<Song> data) {
        this.data = data;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.play_list, container,
                false);
        ListView listView = (ListView) rootView.findViewById(R.id.play_song_list);
        PlayListAdapter playListAdapter = new PlayListAdapter(getContext(), data);
        listView.setAdapter(playListAdapter);
        return rootView;
    }
}