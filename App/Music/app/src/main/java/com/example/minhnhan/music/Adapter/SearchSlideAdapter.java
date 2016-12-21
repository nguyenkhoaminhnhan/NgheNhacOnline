package com.example.minhnhan.music.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.minhnhan.music.Fragment.ImagePlayFragment;
import com.example.minhnhan.music.Fragment.PlayListFragment;
import com.example.minhnhan.music.Fragment.SearchAlbumFragment;
import com.example.minhnhan.music.Fragment.SearchCategoryFragment;
import com.example.minhnhan.music.Fragment.SearchSingerFragment;
import com.example.minhnhan.music.Fragment.SearchSongFragment;
import com.example.minhnhan.music.Model.Song;

import java.util.ArrayList;

/**
 * Created by Minh Nhan on 11/23/2016.
 */

public class SearchSlideAdapter extends FragmentPagerAdapter {

    SearchSongFragment searchSongFragment;
    SearchCategoryFragment searchCategoryFragment;
    SearchSingerFragment searchSingerFragment;
    SearchAlbumFragment searchAlbumFragment;

    public SearchSlideAdapter(FragmentManager fm) {
        super(fm);
        searchSingerFragment = new SearchSingerFragment();
        searchSongFragment = new SearchSongFragment();
        searchAlbumFragment = new SearchAlbumFragment();
        searchCategoryFragment = new SearchCategoryFragment();
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return searchSongFragment;
        else if (position == 1)
            return searchSingerFragment;
        else if (position == 2)
            return searchAlbumFragment;
        else if (position == 3)
            return searchCategoryFragment;
        return searchSongFragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    @Override
    public int getCount() {
        return 2;
    }

    /*public Song get(int position) {

    }*/

    public void update() {
    }
}
