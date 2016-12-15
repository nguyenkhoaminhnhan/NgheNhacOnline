package com.example.minhnhan.music.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.minhnhan.music.Activity.FullScreenPlayActivity;
import com.example.minhnhan.music.Adapter.PlayListAdapter;
import com.example.minhnhan.music.Model.Async.Data.MediaManager;
import com.example.minhnhan.music.Model.Song;
import com.example.minhnhan.music.R;

import java.util.ArrayList;

import static com.example.minhnhan.music.Utils.Utils.AddSongListLinearL;

/**
 * Created by Minh Nhan on 11/23/2016.
 */

public class PlayListFragment extends Fragment {
    public static final String ARG_PAGE = "page";

    public ArrayList<Song> data;
    PlayListAdapter playListAdapter;

    public PlayListFragment(ArrayList<Song> data) {
        this.data = data;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.play_list, container,
                false);
        LinearLayout listView = (LinearLayout) rootView.findViewById(R.id.play_song_list);
        playListAdapter = new PlayListAdapter((FullScreenPlayActivity) getActivity(), data);
        AddSongListLinearL(listView, playListAdapter);
        return rootView;
    }

    public void update() {
        playListAdapter.updatePlaying(MediaManager.getInstance().getCurrentPlayID());
    }
}