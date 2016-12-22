package com.example.minhnhan.music.Fragment;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.minhnhan.music.Adapter.SearchSongApdater;
import com.example.minhnhan.music.Adapter.SongListAdapter;
import com.example.minhnhan.music.Model.Async.AsyncListener;
import com.example.minhnhan.music.Model.Async.AsyncSearchSong;
import com.example.minhnhan.music.Model.Async.Data.DataManager;
import com.example.minhnhan.music.Model.Async.Data.MediaManager;
import com.example.minhnhan.music.Model.Song;
import com.example.minhnhan.music.R;
import com.example.minhnhan.music.Utils.Constants;

import java.util.ArrayList;

import static com.example.minhnhan.music.Utils.Constants.GET_SEARCH_ALL;

/**
 * Created by NKMN on 12/21/2016.
 */

public class SearchSongFragment extends Fragment {
    public static final String ARG_PAGE = "page";

    public ArrayList<Song> data;
    SearchSongApdater songList;
    boolean canLoadMore = true;
    boolean isLoading;
    int page = 1;

    public SearchSongFragment() {
        this.data = DataManager.getInstance().getSong();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.search_song_fragment, container,
                false);
        ListView listView = (ListView) rootView.findViewById(R.id.search_song_list);
        songList = new SearchSongApdater(getActivity(), data);
        listView.setAdapter(songList);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
                                         @Override
                                         public void onScrollStateChanged(AbsListView absListView, int i) {

                                         }

                                         @Override
                                         public void onScroll(AbsListView absListView, int firstVisibleItem,
                                                              int visibleItemCount, int totalItemCount) {
                                             if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0) {
                                                 if (canLoadMore == true) {
                                                     if (!isLoading) {
                                                         isLoading = true;
                                                         AsyncSearchSong asyncSearchSong = new AsyncSearchSong(new AsyncListener() {
                                                             @Override
                                                             public void onAsyncComplete() {
                                                                 if (DataManager.getInstance().getSong().size() > 0) {
                                                                     page++;
                                                                     songList.addMore(DataManager.getInstance().getSong());
                                                                     isLoading = false;
                                                                 } else canLoadMore = false;
                                                             }
                                                         });
                                                         asyncSearchSong.execute(String.format(GET_SEARCH_ALL, DataManager.getInstance().getSearchTXT(), "song", page));
                                                     }
                                                 }
                                             }
                                         }
                                     }

        );
        return rootView;
    }
}
