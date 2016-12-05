package com.example.minhnhan.music.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.minhnhan.music.Adapter.AlbumApdater;
import com.example.minhnhan.music.Model.Async.AsyncAlbumPage;
import com.example.minhnhan.music.Model.Async.AsyncListener;
import com.example.minhnhan.music.Model.Async.Data.DataManager;
import com.example.minhnhan.music.Model.Page.AlbumPage;
import com.example.minhnhan.music.R;

import static com.example.minhnhan.music.Utils.Constants.GET_ALBUM;

/**
 * Created by Minh Nhan on 11/29/2016.
 */

public class AlbumFragment extends Fragment {


    public AlbumFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.album_fragment, container,
                false);

        AlbumPage data = DataManager.getInstance().getAlbumPage();
        if (data == null) {
            AsyncAlbumPage asyncSingerPage = new AsyncAlbumPage(new AsyncListener() {
                @Override
                public void onAsyncComplete() {
                    AlbumPage newData = DataManager.getInstance().getAlbumPage();
                    newView(rootView, newData);
                }
            });
            asyncSingerPage.execute(GET_ALBUM);
        } else
            newView(rootView, data);
        return rootView;
    }

    public View newView(View rootView, AlbumPage data) {
        RecyclerView vietNamView = (RecyclerView) rootView.findViewById(R.id.album_viet_nam);
        vietNamView.setHasFixedSize(true);
        RecyclerView.LayoutManager vietNamLayoutManager = new GridLayoutManager(getContext(), 3);
        vietNamView.setLayoutManager(vietNamLayoutManager);

        RecyclerView auMyView = (RecyclerView) rootView.findViewById(R.id.album_au_my);
        auMyView.setHasFixedSize(true);
        RecyclerView.LayoutManager auMyLayoutManager = new GridLayoutManager(getContext(), 3);
        auMyView.setLayoutManager(auMyLayoutManager);

        RecyclerView chauAView = (RecyclerView) rootView.findViewById(R.id.album_chau_a);
        chauAView.setHasFixedSize(true);
        RecyclerView.LayoutManager ChauALayoutManager = new GridLayoutManager(getContext(), 3);
        chauAView.setLayoutManager(ChauALayoutManager);

        RecyclerView khongLoiView = (RecyclerView) rootView.findViewById(R.id.album_khong_loi);
        khongLoiView.setHasFixedSize(true);
        RecyclerView.LayoutManager khongLoiLayoutManager = new GridLayoutManager(getContext(), 3);
        khongLoiView.setLayoutManager(khongLoiLayoutManager);

        AlbumApdater vietNamAdapter = new AlbumApdater(getContext(), data.getVietNam());
        vietNamView.setAdapter(vietNamAdapter);

        AlbumApdater auMyAdapter = new AlbumApdater(getContext(), data.getAuMy());
        auMyView.setAdapter(auMyAdapter);

        AlbumApdater ChauAAdapter = new AlbumApdater(getContext(), data.getChauA());
        chauAView.setAdapter(ChauAAdapter);

        AlbumApdater khongLoiAdapter = new AlbumApdater(getContext(), data.getKhongLoi());
        khongLoiView.setAdapter(khongLoiAdapter);
        return rootView;
    }
}