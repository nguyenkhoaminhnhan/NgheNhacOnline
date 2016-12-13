package com.example.minhnhan.music.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.minhnhan.music.Fragment.ImagePlayFragment;
import com.example.minhnhan.music.Fragment.PlayListFragment;
import com.example.minhnhan.music.Model.Song;

import java.util.ArrayList;

/**
 * Created by Minh Nhan on 11/23/2016.
 */

public class MyPlayListSlideAdapter extends FragmentPagerAdapter {

    private ArrayList<Song> playlist;
    private Song playingSong;
    ImagePlayFragment imagePlayFragment;
    PlayListFragment playListFragment;

    public MyPlayListSlideAdapter(FragmentManager fm, ArrayList<Song> playlist, Song playingSong) {
        super(fm);
        this.playingSong = playingSong;
        this.playlist = playlist;
        imagePlayFragment = new ImagePlayFragment(playingSong);
        playListFragment = new PlayListFragment(playlist);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return imagePlayFragment;
        else if (position == 1)
            return playListFragment;
        return imagePlayFragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    @Override
    public int getCount() {
        return 2;
    }

    public Song get(int position) {
        return playlist == null ? null : playlist.get(position);
    }

    public void update() {
        imagePlayFragment.update();
        playListFragment.update();
    }
}
