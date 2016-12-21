package com.example.minhnhan.music.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.minhnhan.music.Adapter.CategoryApdater;
import com.example.minhnhan.music.Model.Async.Data.DataManager;
import com.example.minhnhan.music.Model.Category;
import com.example.minhnhan.music.R;

import java.util.ArrayList;

/**
 * Created by Minh Nhan on 11/29/2016.
 */

public class SearchCategoryFragment extends Fragment {
    ProgressDialog progress;

    public SearchCategoryFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.search_si_cat_alb_fragment, container,
                false);
        ArrayList<Category> data = DataManager.getInstance().getCategory();
        RecyclerView catView = (RecyclerView) rootView.findViewById(R.id.search_list);
        catView.setHasFixedSize(true);
        RecyclerView.LayoutManager vietNamLayoutManager = new GridLayoutManager(getContext(), 3);
        catView.setLayoutManager(vietNamLayoutManager);

        CategoryApdater vietNamAdapter = new CategoryApdater(getActivity(), data);
        catView.setAdapter(vietNamAdapter);
        return rootView;
    }
}