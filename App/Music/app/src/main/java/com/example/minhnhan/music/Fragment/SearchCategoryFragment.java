package com.example.minhnhan.music.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.minhnhan.music.Adapter.CategoryApdater;
import com.example.minhnhan.music.Model.Async.AsyncListener;
import com.example.minhnhan.music.Model.Async.AsyncSearchCat;
import com.example.minhnhan.music.Model.Async.AsyncSearchSinger;
import com.example.minhnhan.music.Model.Async.Data.DataManager;
import com.example.minhnhan.music.Model.Category;
import com.example.minhnhan.music.Model.Singer;
import com.example.minhnhan.music.R;

import java.util.ArrayList;

import static com.example.minhnhan.music.Utils.Constants.GET_SEARCH_ALL;

/**
 * Created by Minh Nhan on 11/29/2016.
 */

public class SearchCategoryFragment extends Fragment {
    ProgressDialog progress;
    int page = 1;
    GridLayoutManager layoutManager;
    CategoryApdater catAdapter;

    public SearchCategoryFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.search_si_cat_alb_fragment, container,
                false);
        ImageView icon = (ImageView) rootView.findViewById(R.id.icon);
        TextView infoTXT = (TextView) rootView.findViewById(R.id.info_txt);

        icon.setImageResource(R.drawable.ic_cat);
        infoTXT.setText("Thể loại tìm thấy");

        ArrayList<Category> data = DataManager.getInstance().getCategory();
        RecyclerView catView = (RecyclerView) rootView.findViewById(R.id.search_list);
        catView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getContext(), 3);
        catView.setLayoutManager(layoutManager);

        catAdapter = new CategoryApdater(getActivity(), data);
        catView.setAdapter(catAdapter);

        catView.addOnScrollListener(new MyScroll());
        return rootView;
    }

    class MyScroll extends RecyclerView.OnScrollListener {

        boolean canLoadMore = true;
        boolean isLoading;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
            int totalItemCount = recyclerView.getLayoutManager().getItemCount();
            if (lastVisibleItemPosition - totalItemCount <= 1) {
                if (isLoading || !canLoadMore) {
                    return;
                }
                isLoading = true;
                AsyncSearchCat loadMore = new AsyncSearchCat(new AsyncListener() {
                    @Override
                    public void onAsyncComplete() {
                        ArrayList<Category> temp = DataManager.getInstance().getCategory();
                        canLoadMore = temp != null && temp.size() >= 12;
                        isLoading = false;
                        if (temp.size() > 0) {
                            page++;
                            catAdapter.addMore(DataManager.getInstance().getCategory());
                        }
                    }
                });
                loadMore.execute(String.format(GET_SEARCH_ALL, DataManager.getInstance().getSearchTXT(), "cat", page));
            }
        }
    }
}