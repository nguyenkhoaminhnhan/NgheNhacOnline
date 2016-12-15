package com.example.minhnhan.music.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.minhnhan.music.Fragment.ScreenSlidePageFragment;
import com.example.minhnhan.music.Model.Song;

import java.util.ArrayList;

/**
 * Created by Minh Nhan on 11/23/2016.
 */

public class MySlideAdapter extends FragmentPagerAdapter {

    private ArrayList<Song> data;
    private Song item;
    public int selectedItem;

    public MySlideAdapter(FragmentManager fm, ArrayList<Song> data) {
        super(fm);
        this.data = data;
    }

    @Override
    public Fragment getItem(int position) {
        item = data.get(position);
        return new ScreenSlidePageFragment(position, item);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    @Override
    public int getCount() {
        return data.size();
    }

    public Song get(int position) {
        return data == null ? null : data.get(position);
    }
}
