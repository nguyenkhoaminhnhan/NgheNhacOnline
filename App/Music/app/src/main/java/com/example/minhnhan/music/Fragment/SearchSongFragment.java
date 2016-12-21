package com.example.minhnhan.music.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.minhnhan.music.Adapter.SearchSongApdater;
import com.example.minhnhan.music.Model.Async.Data.DataManager;
import com.example.minhnhan.music.Model.Song;
import com.example.minhnhan.music.R;

import java.util.ArrayList;

/**
 * Created by NKMN on 12/21/2016.
 */

public class SearchSongFragment extends Fragment {
    public static final String ARG_PAGE = "page";

    public ArrayList<Song> data;
    SearchSongApdater songList;

    public SearchSongFragment() {
        this.data = DataManager.getInstance().getSong();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.search_song_fragment, container,
                false);
        RecyclerView listView = (RecyclerView) rootView.findViewById(R.id.search_song_list);
        songList = new SearchSongApdater(getActivity(), data);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        listView.setLayoutManager(layoutManager);
        listView.setAdapter(songList);
        return rootView;
    }
}
