package com.example.minhnhan.music.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.example.minhnhan.music.Adapter.CategoryApdater;
import com.example.minhnhan.music.Model.Async.AsyncCategory;
import com.example.minhnhan.music.Model.Async.AsyncListener;
import com.example.minhnhan.music.Model.Async.Data.DataManager;
import com.example.minhnhan.music.Model.Page.CategoryPage;
import com.example.minhnhan.music.R;

import static com.example.minhnhan.music.Utils.Constants.GET_CATEGORY;

/**
 * Created by Minh Nhan on 11/29/2016.
 */

public class CategoryFragment extends Fragment {
    ProgressDialog progress;

    public CategoryFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.category_fragment, container,
                false);
        progress = new ProgressDialog(getContext());
        progress.setTitle("Vui lòng chờ");
        progress.setMessage("Đang tải...");
        progress.setCancelable(false);
        progress.show();

        AsyncCategory asyncCategory = new AsyncCategory(new AsyncListener() {
            @Override
            public void onAsyncComplete() {
                CategoryPage data = DataManager.getInstance().getCategoryPage();
                RecyclerView vietNamView = (RecyclerView) rootView.findViewById(R.id.viet_nam);
                vietNamView.setHasFixedSize(true);
                RecyclerView.LayoutManager vietNamLayoutManager = new GridLayoutManager(getContext(), 3);
                vietNamView.setLayoutManager(vietNamLayoutManager);

                RecyclerView auMyView = (RecyclerView) rootView.findViewById(R.id.au_my);
                auMyView.setHasFixedSize(true);
                RecyclerView.LayoutManager auMyLayoutManager = new GridLayoutManager(getContext(), 3);
                auMyView.setLayoutManager(auMyLayoutManager);

                RecyclerView chauAView = (RecyclerView) rootView.findViewById(R.id.chau_a);
                chauAView.setHasFixedSize(true);
                RecyclerView.LayoutManager ChauALayoutManager = new GridLayoutManager(getContext(), 3);
                chauAView.setLayoutManager(ChauALayoutManager);

                RecyclerView khongLoiView = (RecyclerView) rootView.findViewById(R.id.khong_loi);
                khongLoiView.setHasFixedSize(true);
                RecyclerView.LayoutManager khongLoiLayoutManager = new GridLayoutManager(getContext(), 3);
                khongLoiView.setLayoutManager(khongLoiLayoutManager);

                CategoryApdater vietNamAdapter = new CategoryApdater(getActivity(), data.getVietNam());
                vietNamView.setAdapter(vietNamAdapter);

                CategoryApdater auMyAdapter = new CategoryApdater(getActivity(), data.getAuMy());
                auMyView.setAdapter(auMyAdapter);

                CategoryApdater ChauAAdapter = new CategoryApdater(getActivity(), data.getChauA());
                chauAView.setAdapter(ChauAAdapter);

                CategoryApdater khongLoiAdapter = new CategoryApdater(getActivity(), data.getKhongLoi());
                khongLoiView.setAdapter(khongLoiAdapter);

                ViewTreeObserver vto = rootView.getViewTreeObserver();
                vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        rootView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        progress.dismiss();
                    }
                });
            }
        });
        asyncCategory.execute(GET_CATEGORY);
        return rootView;
    }
}