package com.example.minhnhan.music.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    private FragmentManager fragmentManager;

    public AlbumFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.album_fragment, container,
                false);

        final TextView moreViet = (TextView) rootView.findViewById(R.id.more_album_viet);

        AsyncAlbumPage asyncSingerPage = new AsyncAlbumPage(new AsyncListener() {
            @Override
            public void onAsyncComplete() {

                AlbumPage data = DataManager.getInstance().getAlbumPage();

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

                AlbumApdater vietNamAdapter = new AlbumApdater((HomeActivity) getActivity(), data.getVietNam());
                vietNamView.setAdapter(vietNamAdapter);

                AlbumApdater auMyAdapter = new AlbumApdater((HomeActivity) getActivity(), data.getAuMy());
                auMyView.setAdapter(auMyAdapter);

                AlbumApdater ChauAAdapter = new AlbumApdater((HomeActivity) getActivity(), data.getChauA());
                chauAView.setAdapter(ChauAAdapter);

                AlbumApdater khongLoiAdapter = new AlbumApdater((HomeActivity) getActivity(), data.getKhongLoi());
                khongLoiView.setAdapter(khongLoiAdapter);

                fragmentManager = getActivity().getSupportFragmentManager();
                moreViet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MoreAlbumFragment moreAlbumFragment = new MoreAlbumFragment("Việt Nam");
                        fragmentManager.beginTransaction()
                                .replace(R.id.album_content, moreAlbumFragment)
                                .commit();
                        getActivity().setTitle("Album Việt Nam");
                    }
                });
            }
        });
        asyncSingerPage.execute(GET_ALBUM);

        return rootView;
    }
}