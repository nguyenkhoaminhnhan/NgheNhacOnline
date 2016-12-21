package com.example.minhnhan.music.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.minhnhan.music.Adapter.SingerApdater;
import com.example.minhnhan.music.Model.Async.Data.DataManager;
import com.example.minhnhan.music.Model.Singer;
import com.example.minhnhan.music.R;

import java.util.ArrayList;

/**
 * Created by Minh Nhan on 11/29/2016.
 */

public class SearchSingerFragment extends Fragment {
    ProgressDialog progress;

    public SearchSingerFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.search_si_cat_alb_fragment, container,
                false);
        ArrayList<Singer> data = DataManager.getInstance().getSinger();
        RecyclerView singerView = (RecyclerView) rootView.findViewById(R.id.search_list);
        singerView.setHasFixedSize(true);
        RecyclerView.LayoutManager vietNamLayoutManager = new GridLayoutManager(getContext(), 3);
        singerView.setLayoutManager(vietNamLayoutManager);

        SingerApdater singerApdater = new SingerApdater(getActivity(), data);
        singerView.setAdapter(singerApdater);
        return rootView;
    }
}