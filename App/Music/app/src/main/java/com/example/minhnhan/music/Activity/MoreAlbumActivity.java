package com.example.minhnhan.music.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.minhnhan.music.Adapter.AlbumApdater;
import com.example.minhnhan.music.Adapter.MoreAlbumApdater;
import com.example.minhnhan.music.Model.Album;
import com.example.minhnhan.music.Model.Async.AsyncAlbum;
import com.example.minhnhan.music.Model.Async.AsyncAlbumSong;
import com.example.minhnhan.music.Model.Async.AsyncListener;
import com.example.minhnhan.music.Model.Async.Data.DataManager;
import com.example.minhnhan.music.R;
import com.example.minhnhan.music.Utils.Constants;

import java.util.ArrayList;

import static com.example.minhnhan.music.Utils.Constants.MORE_ALBUM;

public class MoreAlbumActivity extends AppCompatActivity {

    private int type = 1;
    private int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_album);
        this.setTitle("Album");
        Bundle b = getIntent().getExtras();
        if (b != null)
            type = b.getInt("key");

        TextView nameList = (TextView) findViewById(R.id.more_album_list_name);
        ImageView image = (ImageView) findViewById(R.id.more_album_icon);
        if (type == 1) {
            nameList.setText("Việt Nam");
            image.setImageResource(R.drawable.vietnam);
        } else if (type == 2) {
            nameList.setText("Âu Mỹ");
            image.setImageResource(R.drawable.au_my);
        } else if (type == 3) {
            nameList.setText("Châu Á");
            image.setImageResource(R.drawable.chau_a);
        } else if (type == 4) {
            nameList.setText("Không Lời");
            image.setImageResource(R.drawable.khong_loi);
        }

        AsyncAlbum asyncAlbum = new AsyncAlbum(new AsyncListener() {
            @Override
            public void onAsyncComplete() {
                page++;
                ArrayList<Album> data = DataManager.getInstance().getMoreAlbum();

                RecyclerView catView = (RecyclerView) findViewById(R.id.more_cat_content);

                catView.setHasFixedSize(true);
                RecyclerView.LayoutManager vietNamLayoutManager = new GridLayoutManager(MoreAlbumActivity.this, 3);
                catView.setLayoutManager(vietNamLayoutManager);


                final AlbumApdater moreAlbumApdater = new AlbumApdater(MoreAlbumActivity.this, data);
                catView.setAdapter(moreAlbumApdater);
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
                                        moreAlbumApdater.addMore(DataManager.getInstance().getMoreAlbum());
                                    }
                                }
                            });
                            loadMore.execute(String.format(MORE_ALBUM, type, page));
                        }
                    }
                });

            }
        });
        asyncAlbum.execute(String.format(MORE_ALBUM, type, page));
    }
}
