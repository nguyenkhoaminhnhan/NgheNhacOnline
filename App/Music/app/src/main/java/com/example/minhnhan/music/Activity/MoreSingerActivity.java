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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.minhnhan.music.Adapter.SingerApdater;
import com.example.minhnhan.music.Model.Async.AsyncListener;
import com.example.minhnhan.music.Model.Async.AsyncSinger;
import com.example.minhnhan.music.Model.Async.Data.DataManager;
import com.example.minhnhan.music.Model.Async.Data.MediaManager;
import com.example.minhnhan.music.Model.Singer;
import com.example.minhnhan.music.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

import static com.example.minhnhan.music.Utils.Constants.GET_SINGER_AU_MY;
import static com.example.minhnhan.music.Utils.Constants.GET_SINGER_CHAU_A;
import static com.example.minhnhan.music.Utils.Constants.GET_SINGER_HOA_TAU;
import static com.example.minhnhan.music.Utils.Constants.GET_SINGER_VN;

public class MoreSingerActivity extends AppCompatActivity {

    private int type = 1;
    private int page = 0;
    private String url;
    private ImageView songImage;
    private TextView plName;
    private TextView plSinger;
    private ImageView preButton;
    private ImageView playButton;
    private ImageView nextButton;
    private RelativeLayout plFrame;
    private DisplayImageOptions options;
    private ImageLoader imageLoader;
    GridLayoutManager layoutManager;
    SingerApdater singerApdater;

    ProgressDialog progress;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_singer);

        progress = new ProgressDialog(this);
        progress.setTitle("Vui lòng chờ");
        progress.setMessage("Đang tải...");
        progress.setCancelable(false);
        progress.show();

        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        songImage = (ImageView) findViewById(R.id.more_si_pl_image);
        plName = (TextView) findViewById(R.id.more_si_pl_song_name);
        plSinger = (TextView) findViewById(R.id.more_si_pl_singer_name);
        playButton = (ImageView) findViewById(R.id.more_si_pl_play_pause);
        plFrame = (RelativeLayout) findViewById(R.id.more_si_pl_frame);
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_stub)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.default_image).cacheInMemory(true)
                .cacheOnDisk(true).considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565).build();

        if (MediaManager.getInstance().getmPlayer().isPlaying()) {
            updatePlayBack();
        }

        preButton = (ImageView) findViewById(R.id.more_si_pl_prev);
        nextButton = (ImageView) findViewById(R.id.more_si_pl_next);

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

        this.setTitle("Singer");
        Bundle b = getIntent().getExtras();
        if (b != null)
            type = b.getInt("key");

        TextView nameList = (TextView) findViewById(R.id.more_singer_list_name);
        ImageView image = (ImageView) findViewById(R.id.more_singer_icon);
        if (type == 1) {
            nameList.setText("Việt Nam");
            image.setImageResource(R.drawable.vietnam);
            url = GET_SINGER_VN;
        } else if (type == 2) {
            nameList.setText("Âu Mỹ");
            image.setImageResource(R.drawable.au_my);
            url = GET_SINGER_AU_MY;
        } else if (type == 3) {
            nameList.setText("Châu Á");
            image.setImageResource(R.drawable.chau_a);
            url = GET_SINGER_CHAU_A;
        } else if (type == 4) {
            nameList.setText("Hòa Tấu");
            image.setImageResource(R.drawable.khong_loi);
            url = GET_SINGER_HOA_TAU;
        }

        AsyncSinger asyncAlbum = new AsyncSinger(new AsyncListener() {
            @Override
            public void onAsyncComplete() {
                page++;
                ArrayList<Singer> data = DataManager.getInstance().getMoreSinger();

                RecyclerView contentView = (RecyclerView) findViewById(R.id.more_singer_content);

                contentView.setHasFixedSize(true);
                layoutManager = new GridLayoutManager(MoreSingerActivity.this, 3);
                contentView.setLayoutManager(layoutManager);


                singerApdater = new SingerApdater(MoreSingerActivity.this, data);
                contentView.setAdapter(singerApdater);
                contentView.addOnScrollListener(new MyScroll());

                view = findViewById(R.id.activity_more_singer);
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
        asyncAlbum.execute(url + page);
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
                LinearLayout plInfo = (LinearLayout) findViewById(R.id.more_si_pl_info);
                plInfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MediaManager.getInstance().isContinue = true;
                        Intent i = new Intent(MoreSingerActivity.this, FullScreenPlayActivity.class);
                        MoreSingerActivity.this.startActivityForResult(i, 11);
                    }
                });
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
                AsyncSinger loadMore = new AsyncSinger(new AsyncListener() {
                    @Override
                    public void onAsyncComplete() {
                        ArrayList<Singer> temp = DataManager.getInstance().getMoreSinger();
                        canLoadMore = temp != null && temp.size() >= 12;
                        isLoading = false;
                        if (temp.size() > 0) {
                            page++;
                            singerApdater.addMore(DataManager.getInstance().getMoreSinger());
                        }
                    }
                });
                loadMore.execute(url + page);
            }
        }
    }
}
