package com.example.minhnhan.music.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.minhnhan.music.Adapter.AlbumApdater;
import com.example.minhnhan.music.Adapter.SongListAdapter;
import com.example.minhnhan.music.Model.Album;
import com.example.minhnhan.music.Model.Async.AsyncAlbum;
import com.example.minhnhan.music.Model.Async.AsyncAlbumSong;
import com.example.minhnhan.music.Model.Async.AsyncListener;
import com.example.minhnhan.music.Model.Async.Data.DataManager;
import com.example.minhnhan.music.Model.Async.Data.MediaManager;
import com.example.minhnhan.music.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import static com.example.minhnhan.music.Utils.Constants.GET_ALBUM_BY_CAT;
import static com.example.minhnhan.music.Utils.Constants.GET_SONG_BY_CAT;
import static com.example.minhnhan.music.Utils.Constants.MORE_ALBUM;
import static com.example.minhnhan.music.Utils.Utils.AddSongListLinearL;

public class CategoryDetailActivity extends AppCompatActivity {
    private int page = 0;

    String typeName;
    long typeID;

    private ImageView songImage;
    private TextView plName;
    private TextView plSinger;
    private ImageView preButton;
    private ImageView playButton;
    private ImageView nextButton;
    private LinearLayout plFrame;
    private DisplayImageOptions options;
    private ImageLoader imageLoader;

    AlbumApdater moreAlbumApdater;
    GridLayoutManager layoutManager;
    ProgressDialog progress;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);

        progress = new ProgressDialog(this);
        progress.setTitle("Vui lòng chờ");
        progress.setMessage("Đang tải...");
        progress.setCancelable(false);
        progress.show();

        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        songImage = (ImageView) findViewById(R.id.dt_cat_pl_image);
        plName = (TextView) findViewById(R.id.dt_cat_pl_song_name);
        plSinger = (TextView) findViewById(R.id.dt_cat_pl_singer_name);
        playButton = (ImageView) findViewById(R.id.dt_cat_pl_play_pause);
        plFrame = (LinearLayout) findViewById(R.id.dt_cat_pl_frame);
        ImageView background = (ImageView)findViewById(R.id.dt_cat_background);

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_stub)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.default_image).cacheInMemory(true)
                .cacheOnDisk(true).considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565).build();

        if (MediaManager.getInstance().getmPlayer().isPlaying()) {
            updatePlayBack();
        }

        preButton = (ImageView) findViewById(R.id.dt_cat_pl_prev);
        nextButton = (ImageView) findViewById(R.id.dt_cat_pl_next);

        if (MediaManager.getInstance().isPlayed) {
            updatePlayBack();
            MediaManager.getInstance().setPlayListener(listener);
            MediaManager.getInstance().setPlayCompleteListener(playCompleteListener);
        }

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mPlayer = MediaManager.getInstance().getmPlayer();
                if (mPlayer.isPlaying()) {
                    mPlayer.pause();
                    playButton.setImageResource(R.drawable.uamp_ic_play_arrow_48dp_black);
                } else if (!mPlayer.isPlaying()) {
                    mPlayer.start();
                    playButton.setImageResource(R.drawable.uamp_ic_pause_48dp_black);
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MediaManager.getInstance().next()) {
                    imageLoader.displayImage(MediaManager.getInstance().getPlayingSong().getImagePath(),
                            songImage, options, null);
                    plName.setText(MediaManager.getInstance().getPlayingSong().name);
                    plSinger.setText(MediaManager.getInstance().getPlayingSong().singer);
                    playButton.setImageResource(R.drawable.uamp_ic_play_arrow_48dp_black);
                }
            }
        });
        preButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MediaManager.getInstance().prev()) {
                    imageLoader.displayImage(MediaManager.getInstance().getPlayingSong().getImagePath(),
                            songImage, options, null);
                    plName.setText(MediaManager.getInstance().getPlayingSong().name);
                    plSinger.setText(MediaManager.getInstance().getPlayingSong().singer);
                    playButton.setImageResource(R.drawable.uamp_ic_play_arrow_48dp_black);
                }
            }
        });

        Bundle b = getIntent().getExtras();
        if (b != null) {
            typeID = b.getLong("id");
            typeName = b.getString("key");
            this.setTitle(typeName);
            String imagePath = b.getString("path");
            imageLoader.displayImage(imagePath,
                    background, options, null);
        }


        AsyncAlbumSong asyncAlbumSong = new AsyncAlbumSong(new AsyncListener() {
            @Override
            public void onAsyncComplete() {
                LinearLayout listSong = (LinearLayout) findViewById(R.id.song_cat);
                SongListAdapter data = new SongListAdapter
                        (CategoryDetailActivity.this, MediaManager.getInstance().getPrepareList());
                AddSongListLinearL(listSong, data);
            }
        });
        asyncAlbumSong.execute(String.format(GET_SONG_BY_CAT, typeID, page));

        AsyncAlbum asyncAlbum = new AsyncAlbum(new AsyncListener() {
            @Override
            public void onAsyncComplete() {
                page++;
                ArrayList<Album> data = DataManager.getInstance().getMoreAlbum();

                RecyclerView albumView = (RecyclerView) findViewById(R.id.album_cat);

                albumView.setHasFixedSize(true);
                layoutManager = new GridLayoutManager(CategoryDetailActivity.this, 2);
                albumView.setLayoutManager(layoutManager);


                moreAlbumApdater = new AlbumApdater(CategoryDetailActivity.this, data);
                albumView.setAdapter(moreAlbumApdater);
                albumView.addOnScrollListener(new MyScroll());

                view = findViewById(R.id.activity_category_detail);
                ViewTreeObserver vto = view.getViewTreeObserver();
                vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        progress.dismiss();
                    }
                });
            }
        });
        try {
            typeName = URLEncoder.encode(typeName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        asyncAlbum.execute(String.format(GET_ALBUM_BY_CAT, typeName, page));
    }

    MediaManager.IPlayListener listener = new MediaManager.IPlayListener() {
        @Override
        public void onPlay(int currentPlayID) {
            updatePlayBack();
        }
    };
    MediaManager.IPlayCompleteListener playCompleteListener = new MediaManager.IPlayCompleteListener() {
        @Override
        public void onPlayComplete() {
            if (MediaManager.getInstance().next())
                updatePlayBack();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 11) {
            if (MediaManager.getInstance().isPlayed) {
                updatePlayBack();
                MediaManager.getInstance().setPlayListener(listener);
                MediaManager.getInstance().setPlayCompleteListener(playCompleteListener);
            }
        }
    }

    public void updatePlayBack() {
        MediaPlayer mPlayer = MediaManager.getInstance().getmPlayer();
        plFrame.setVisibility(View.VISIBLE);
        imageLoader.displayImage(MediaManager.getInstance().getPlayingSong().getImagePath(),
                songImage, options, null);
        plName.setText(MediaManager.getInstance().getPlayingSong().name);
        plSinger.setText(MediaManager.getInstance().getPlayingSong().singer);
        if (mPlayer.isPlaying()) {
            playButton.setImageResource(R.drawable.uamp_ic_pause_48dp_black);
        } else {
            playButton.setImageResource(R.drawable.uamp_ic_play_arrow_48dp_black);
        }
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
                AsyncAlbum loadMore = new AsyncAlbum(new AsyncListener() {
                    @Override
                    public void onAsyncComplete() {
                        ArrayList<Album> temp = DataManager.getInstance().getMoreAlbum();
                        canLoadMore = temp != null && temp.size() >= 12;
                        isLoading = false;
                        if (temp.size() > 0) {
                            page++;
                            moreAlbumApdater.addMore(DataManager.getInstance().getMoreAlbum());
                        }
                    }
                });
                loadMore.execute(String.format(GET_ALBUM_BY_CAT, typeName, page));
            }
        }
    }
}
