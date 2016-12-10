package com.example.minhnhan.music.Activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.minhnhan.music.Adapter.AlbumApdater;
import com.example.minhnhan.music.Model.Album;
import com.example.minhnhan.music.Model.Async.AsyncAlbumPage;
import com.example.minhnhan.music.Model.Async.AsyncListener;
import com.example.minhnhan.music.Model.Async.Data.AsyncAlbum;
import com.example.minhnhan.music.Model.Async.Data.DataManager;
import com.example.minhnhan.music.Model.Page.AlbumPage;
import com.example.minhnhan.music.R;
import com.example.minhnhan.music.Utils.EndlessRecyclerOnScrollListener;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import static com.example.minhnhan.music.Utils.Constants.GET_ALBUM;
import static com.example.minhnhan.music.Utils.Constants.SEARCH_ALBUM;

/**
 * Created by Minh Nhan on 12/10/2016.
 */

public class MoreAlbumFragment extends android.support.v4.app.Fragment implements AsyncListener {
    private String cat;
    private boolean loading = true;
    private int page = 0;

    public MoreAlbumFragment(String cat) {
        this.cat = cat;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.more_album, container,
                false);
        TextView nameList = (TextView) rootView.findViewById(R.id.more_album_list_name);
        nameList.setText(cat);

        final AsyncAlbum asyncAlbum = new AsyncAlbum(new AsyncListener() {
            @Override
            public void onAsyncComplete() {
                page++;
                ArrayList<Album> data = DataManager.getInstance().getMoreAlbum();

                RecyclerView catView = (RecyclerView) rootView.findViewById(R.id.more_cat_content);

                catView.setHasFixedSize(true);
                RecyclerView.LayoutManager vietNamLayoutManager = new GridLayoutManager(getContext(), 3);
                catView.setLayoutManager(vietNamLayoutManager);


                final AlbumApdater catAdapter = new AlbumApdater((HomeActivity) getActivity(), data);
                catView.setAdapter(catAdapter);
                catView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        int visibleItemCount = recyclerView.getChildCount();
                        int totalItemCount = recyclerView.getLayoutManager().getItemCount();
                        if ((totalItemCount - visibleItemCount) == 0) {
                            AsyncAlbum loadMore = new AsyncAlbum(new AsyncListener() {
                                @Override
                                public void onAsyncComplete() {
                                    ArrayList<Album> temp = DataManager.getInstance().getMoreAlbum();
                                    if (temp.size() > 0) {
                                        page++;
                                        catAdapter.addMore(DataManager.getInstance().getMoreAlbum());
                                    }
                                }
                            });
                            loadMore.execute(String.format(SEARCH_ALBUM, cat, page));
                        }
                    }
                });

            }
        });
        try {
            cat = URLEncoder.encode(cat, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        asyncAlbum.execute(String.format(SEARCH_ALBUM, cat, page));

        return rootView;
    }

    @Override
    public void onAsyncComplete() {

    }
}