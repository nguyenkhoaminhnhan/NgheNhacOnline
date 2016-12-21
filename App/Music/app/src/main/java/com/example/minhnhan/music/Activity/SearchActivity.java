package com.example.minhnhan.music.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.minhnhan.music.Adapter.SearchSlideAdapter;
import com.example.minhnhan.music.Model.Async.Data.MediaManager;
import com.example.minhnhan.music.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.viewpagerindicator.UnderlinePageIndicator;

public class SearchActivity extends AppCompatActivity {

    private ImageView songImage;
    private TextView plName;
    private TextView plSinger;
    private ImageView preButton;
    private ImageView playButton;
    private ImageView nextButton;
    private RelativeLayout plFrame;
    private DisplayImageOptions options;
    private ImageLoader imageLoader;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        songImage = (ImageView) findViewById(R.id.search_pl_image);
        plName = (TextView) findViewById(R.id.search_pl_song_name);
        plSinger = (TextView) findViewById(R.id.search_pl_singer_name);
        playButton = (ImageView) findViewById(R.id.search_pl_play_pause);
        plFrame = (RelativeLayout) findViewById(R.id.search_pl_frame);
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_stub)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.default_image).cacheInMemory(true)
                .cacheOnDisk(true).considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565).build();

        preButton = (ImageView) findViewById(R.id.search_pl_prev);
        nextButton = (ImageView) findViewById(R.id.search_pl_next);

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

        if (MediaManager.getInstance().getmPlayer().isPlaying()) {
            updatePlayBack();
        }

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

        ViewPager viewPager = (ViewPager) findViewById(R.id.search_content);
        SearchSlideAdapter searchSlideAdapter = new SearchSlideAdapter(getSupportFragmentManager());
        viewPager.setAdapter(searchSlideAdapter);

        UnderlinePageIndicator indicator = (UnderlinePageIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0)
                    SearchActivity.this.setTitle("Bài Hát");
                else if (position == 1)
                    SearchActivity.this.setTitle("Ca Sĩ");
                else if (position == 2)
                    SearchActivity.this.setTitle("Album");
                else if (position == 3)
                    SearchActivity.this.setTitle("Thể Loại");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 11) {
            if (MediaManager.getInstance().isPlayed) {
                updatePlayBack();
                MediaManager.getInstance().setPlayListener(listener);
                MediaManager.getInstance().setPlayCompleteListener(playCompleteListener);
                LinearLayout plInfo = (LinearLayout) findViewById(R.id.search_pl_info);
                plInfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MediaManager.getInstance().isContinue = true;
                        Intent i = new Intent(SearchActivity.this, FullScreenPlayActivity.class);
                        SearchActivity.this.startActivityForResult(i, 11);
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
}
