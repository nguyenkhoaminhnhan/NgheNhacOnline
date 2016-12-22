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

import com.example.minhnhan.music.Adapter.SingerApdater;
import com.example.minhnhan.music.Model.Album;
import com.example.minhnhan.music.Model.Async.AsyncAlbum;
import com.example.minhnhan.music.Model.Async.AsyncListener;
import com.example.minhnhan.music.Model.Async.AsyncSearchSinger;
import com.example.minhnhan.music.Model.Async.Data.DataManager;
import com.example.minhnhan.music.Model.Singer;
import com.example.minhnhan.music.R;

import java.util.ArrayList;

import static com.example.minhnhan.music.Utils.Constants.GET_SEARCH_ALL;
import static com.example.minhnhan.music.Utils.Constants.MORE_ALBUM;

/**
 * Created by Minh Nhan on 11/29/2016.
 */

public class SearchSingerFragment extends Fragment {
    ProgressDialog progress;
    GridLayoutManager layoutManager;
    int page = 1;
    SingerApdater singerApdater;

    public SearchSingerFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.search_si_cat_alb_fragment, container,
                false);
        ImageView icon = (ImageView) rootView.findViewById(R.id.icon);
        TextView infoTXT = (TextView) rootView.findViewById(R.id.info_txt);

        icon.setImageResource(R.drawable.ic_singer_info);
        infoTXT.setText("Ca sĩ tìm thấy");
        ArrayList<Singer> data = DataManager.getInstance().getSinger();
        RecyclerView singerView = (RecyclerView) rootView.findViewById(R.id.search_list);
        singerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getContext(), 3);
        singerView.setLayoutManager(layoutManager);

        singerApdater = new SingerApdater(getActivity(), data);
        singerView.setAdapter(singerApdater);

        singerView.addOnScrollListener(new MyScroll());
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
                AsyncSearchSinger loadMore = new AsyncSearchSinger(new AsyncListener() {
                    @Override
                    public void onAsyncComplete() {
                        ArrayList<Singer> temp = DataManager.getInstance().getSinger();
                        canLoadMore = temp != null && temp.size() >= 12;
                        isLoading = false;
                        if (temp.size() > 0) {
                            page++;
                            singerApdater.addMore(DataManager.getInstance().getSinger());
                        }
                    }
                });
                loadMore.execute(String.format(GET_SEARCH_ALL, DataManager.getInstance().getSearchTXT(), "singer", page));
            }
        }
    }
}