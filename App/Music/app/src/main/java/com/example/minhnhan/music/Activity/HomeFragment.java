package com.example.minhnhan.music.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.minhnhan.music.Adapter.HotSongAdapter;
import com.example.minhnhan.music.Adapter.MySlideAdapter;
import com.example.minhnhan.music.Adapter.SongListAdapter;
import com.example.minhnhan.music.Model.Async.Data.DataManager;
import com.example.minhnhan.music.R;
import com.example.minhnhan.music.Utils.DepthPageTransformer;

import static com.example.minhnhan.music.Utils.Utils.AddHotSongLinearL;
import static com.example.minhnhan.music.Utils.Utils.AddSongListLinearL;

/**
 * Created by Minh Nhan on 11/29/2016.
 */

public class HomeFragment extends Fragment {


    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment, container,
                false);
        /*-------------------Get params for LinearLayout---------------------------*/
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(4 * metrics.widthPixels / 10,
                LinearLayout.LayoutParams.MATCH_PARENT);


        /*-------------------Show Case---------------------------*/
        FragmentManager manager = getChildFragmentManager();
        MySlideAdapter showCase = new MySlideAdapter(manager,
                DataManager.getInstance().getHomeDetail().getShowCase());
        ViewPager showCasePager = (ViewPager) rootView.findViewById(R.id.show_case);
        showCasePager.setPageTransformer(true, new DepthPageTransformer());
        showCasePager.setAdapter(showCase);
        /*-------------------Hot Song Week---------------------------*/
        LinearLayout hotSongWeekView = (LinearLayout) rootView.findViewById(R.id.hot_song_week);
        HotSongAdapter hotSongWeek = new HotSongAdapter((HomeActivity) getActivity(),
                DataManager.getInstance().getHomeDetail().getHotSongWeek());
        AddHotSongLinearL(hotSongWeekView, hotSongWeek, params);

        /*-------------------Hot Song Month---------------------------*/
        LinearLayout hotSongMonthView = (LinearLayout) rootView.findViewById(R.id.hot_song_month);
        HotSongAdapter hotSongMonth = new HotSongAdapter((HomeActivity) getActivity(),
                DataManager.getInstance().getHomeDetail().getHotSongMonth());
        AddHotSongLinearL(hotSongMonthView, hotSongMonth, params);
        /*-------------------Song List---------------------------*/
        LinearLayout hotSongView = (LinearLayout) rootView.findViewById(R.id.popular_container);
        SongListAdapter hotSong = new SongListAdapter((HomeActivity)getActivity(),
                DataManager.getInstance().getHomeDetail().getHotSong());
        AddSongListLinearL(hotSongView, hotSong);
        return rootView;
    }
}