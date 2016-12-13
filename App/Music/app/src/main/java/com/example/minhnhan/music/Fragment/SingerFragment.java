package com.example.minhnhan.music.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.minhnhan.music.Activity.MoreAlbumActivity;
import com.example.minhnhan.music.Activity.MoreSingerActivity;
import com.example.minhnhan.music.Adapter.CategoryApdater;
import com.example.minhnhan.music.Adapter.SingerApdater;
import com.example.minhnhan.music.Model.Async.AsyncCategory;
import com.example.minhnhan.music.Model.Async.AsyncListener;
import com.example.minhnhan.music.Model.Async.AsyncSingerPage;
import com.example.minhnhan.music.Model.Async.Data.DataManager;
import com.example.minhnhan.music.Model.Page.CategoryPage;
import com.example.minhnhan.music.Model.Page.SingerPage;
import com.example.minhnhan.music.R;

import static com.example.minhnhan.music.Utils.Constants.GET_CATEGORY;
import static com.example.minhnhan.music.Utils.Constants.GET_SINGER;

/**
 * Created by Minh Nhan on 11/29/2016.
 */

public class SingerFragment extends Fragment {


    public SingerFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.singer_fragment, container,
                false);
        final TextView moreViet = (TextView) rootView.findViewById(R.id.more_singer_viet);
        final TextView moreAuMy = (TextView) rootView.findViewById(R.id.more_singer_au_my);
        final TextView moreChauA = (TextView) rootView.findViewById(R.id.more_singer_chau_a);
        final TextView moreKhongLoi = (TextView) rootView.findViewById(R.id.more_singer_hoa_tau);


        AsyncSingerPage asyncSingerPage = new AsyncSingerPage(new AsyncListener() {
            @Override
            public void onAsyncComplete() {
                SingerPage data = DataManager.getInstance().getSingerPage();
                RecyclerView vietNamView = (RecyclerView) rootView.findViewById(R.id.singer_viet_nam);
                vietNamView.setHasFixedSize(true);
                RecyclerView.LayoutManager vietNamLayoutManager = new GridLayoutManager(getContext(), 3);
                vietNamView.setLayoutManager(vietNamLayoutManager);

                RecyclerView auMyView = (RecyclerView) rootView.findViewById(R.id.singer_au_my);
                auMyView.setHasFixedSize(true);
                RecyclerView.LayoutManager auMyLayoutManager = new GridLayoutManager(getContext(), 3);
                auMyView.setLayoutManager(auMyLayoutManager);

                RecyclerView chauAView = (RecyclerView) rootView.findViewById(R.id.singer_chau_a);
                chauAView.setHasFixedSize(true);
                RecyclerView.LayoutManager ChauALayoutManager = new GridLayoutManager(getContext(), 3);
                chauAView.setLayoutManager(ChauALayoutManager);

                RecyclerView hoaTauView = (RecyclerView) rootView.findViewById(R.id.singer_hoa_tau);
                hoaTauView.setHasFixedSize(true);
                RecyclerView.LayoutManager khongLoiLayoutManager = new GridLayoutManager(getContext(), 3);
                hoaTauView.setLayoutManager(khongLoiLayoutManager);

                SingerApdater vietNamAdapter = new SingerApdater(getContext(), data.getVietNam());
                vietNamView.setAdapter(vietNamAdapter);

                SingerApdater auMyAdapter = new SingerApdater(getContext(), data.getAuMy());
                auMyView.setAdapter(auMyAdapter);

                SingerApdater ChauAAdapter = new SingerApdater(getContext(), data.getChauA());
                chauAView.setAdapter(ChauAAdapter);

                SingerApdater hoaTauAdapter = new SingerApdater(getContext(), data.getHoaTau());
                hoaTauView.setAdapter(hoaTauAdapter);

                moreViet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getActivity(), MoreSingerActivity.class);
                        Bundle b = new Bundle();
                        b.putInt("key", 1);
                        i.putExtras(b);
                        startActivityForResult(i,11);
                    }
                });
                moreAuMy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getActivity(), MoreSingerActivity.class);
                        Bundle b = new Bundle();
                        b.putInt("key", 2);
                        i.putExtras(b);
                        startActivityForResult(i,11);
                    }
                });
                moreChauA.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getActivity(), MoreSingerActivity.class);
                        Bundle b = new Bundle();
                        b.putInt("key", 3);
                        i.putExtras(b);
                        startActivityForResult(i,11);
                    }
                });
                moreKhongLoi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getActivity(), MoreSingerActivity.class);
                        Bundle b = new Bundle();
                        b.putInt("key", 4);
                        i.putExtras(b);
                        startActivityForResult(i,11);
                    }
                });
            }
        });
        asyncSingerPage.execute(GET_SINGER);

        return rootView;
    }
}