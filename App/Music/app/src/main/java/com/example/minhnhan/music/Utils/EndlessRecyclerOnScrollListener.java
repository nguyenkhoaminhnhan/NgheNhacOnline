package com.example.minhnhan.music.Utils;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Minh Nhan on 12/11/2016.
 */

public abstract class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener {
    public static String TAG = EndlessRecyclerOnScrollListener.class.getSimpleName();

    private int previousTotal = 0; // The total number of items in the dataset after the last load
    private boolean loading = true; // True if we are still waiting for the last set of data to load.
    private int visibleThreshold = 5; // The minimum amount of items to have below your current scroll position before loading more.
    int firstVisibleItem, visibleItemCount, totalItemCount;

    private int currentPage = 1;

    private RecyclerView.LayoutManager mLayoutManager;
    private boolean isUseGridLayoutManager;

    public EndlessRecyclerOnScrollListener(GridLayoutManager gridLayoutManager) {
        this.mLayoutManager = gridLayoutManager;
        isUseGridLayoutManager = true;

    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mLayoutManager.getItemCount();

        if (isUseGridLayoutManager && mLayoutManager instanceof GridLayoutManager) {
            firstVisibleItem = ((GridLayoutManager) mLayoutManager).findFirstVisibleItemPosition();
        }

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount)
                <= (firstVisibleItem + visibleThreshold)) {
            // End has been reached

            // Do something
            currentPage++;

            loading = true;
        }
    }
}