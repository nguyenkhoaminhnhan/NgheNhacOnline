package com.example.minhnhan.music.Activity;

import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.minhnhan.music.Adapter.MyPlayListSlideAdapter;
import com.example.minhnhan.music.Adapter.MySlideAdapter;
import com.example.minhnhan.music.Model.Async.Data.DataManager;
import com.example.minhnhan.music.Model.Async.Data.MediaManager;
import com.example.minhnhan.music.R;
import com.example.minhnhan.music.Utils.DepthPageTransformer;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class FullScreenPlayActivity extends AppCompatActivity {

    private TextView songName;
    private TextView singerName;
    private TextView realTime;
    private TextView endTime;
    ImageView preButton;
    ImageView playButton;
    ImageView nextButton;
    SeekBar seekBar;
    MyPlayListSlideAdapter playlist;
    private int currentTime;
    private int mediaFileLength;
    private DateFormat formatter;

    private final Handler handler = new Handler();

    MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*------------------Create view---------------------------------------*/
        setContentView(R.layout.activity_full_screen_play);
        songName = (TextView) findViewById(R.id.play_song_name);
        singerName = (TextView) findViewById(R.id.play_singer_name);
        preButton = (ImageView) findViewById(R.id.prev);
        playButton = (ImageView) findViewById(R.id.play_pause);
        nextButton = (ImageView) findViewById(R.id.next);
        realTime = (TextView) findViewById(R.id.real_time);
        endTime = (TextView) findViewById(R.id.end_time);
        seekBar = (SeekBar) findViewById(R.id.seekBar1);

        /*---------------------set adapter------------------------------------*/
        FragmentManager manager = getSupportFragmentManager();
        final MyPlayListSlideAdapter playlist = new MyPlayListSlideAdapter(manager,
                MediaManager.getInstance().getPlayList(),
                MediaManager.getInstance().getPlayingSong());
        /*---------------------set pager--------------------------------------*/
        ViewPager bodyPlay = (ViewPager) findViewById(R.id.body_play);
        bodyPlay.setPageTransformer(true, new DepthPageTransformer());


        bodyPlay.setAdapter(playlist);

        mPlayer = MediaManager.getInstance().getmPlayer();

        MediaManager.getInstance().resetCurrent();
        MediaManager.getInstance().play();
        newMediaPlayer();


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaManager.getInstance().next();
                newMediaPlayer();
                playlist.update();
            }
        });
        preButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaManager.getInstance().prev();
                newMediaPlayer();
                playlist.update();
            }
        });
    }

    private void SeekBarProgressUpdater() {
        currentTime = mPlayer.getCurrentPosition();
        realTime.setText(formatter.format(currentTime));
        seekBar.setProgress(currentTime);
        if (mPlayer.isPlaying()) {
            Runnable notification = new Runnable() {
                public void run() {
                    SeekBarProgressUpdater();
                }
            };
            handler.post(notification);
        }
    }

    private void newMediaPlayer() {
        playButton.setImageResource(R.drawable.uamp_ic_pause_white_48dp);
        songName.setText(MediaManager.getInstance().getPlayingSong().name);
        singerName.setText(MediaManager.getInstance().getPlayingSong().singer);
        mediaFileLength = mPlayer.getDuration();
        seekBar.setMax(mediaFileLength);
        formatter = new SimpleDateFormat("mm:ss");
        endTime.setText(formatter.format(mediaFileLength));
        SeekBarProgressUpdater();

        /*------------------Update play complete---------------------------------------*/
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                int currentPlayID = MediaManager.getInstance().getCurrentPlayID();
                if (currentPlayID == MediaManager.getInstance().getDataSize() - 1) {
                    playButton.setImageResource(R.drawable.uamp_ic_play_arrow_white_48dp);
                    seekBar.setProgress(0);
                    currentTime = 0;
                    realTime.setText(formatter.format(currentTime));
                    MediaManager.getInstance().resetCurrent();

                } else {
                    seekBar.setProgress(0);
                    currentTime = 0;
                    realTime.setText(formatter.format(currentTime));
                    MediaManager.getInstance().next();
                    newMediaPlayer();
                }

            }
        });

        /*------------------Play/Pause media---------------------------------------*/
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPlayer.isPlaying()) {
                    mPlayer.pause();
                    playButton.setImageResource(R.drawable.uamp_ic_play_arrow_white_48dp);
                } else if (!mPlayer.isPlaying()) {
                    mPlayer.start();
                    playButton.setImageResource(R.drawable.uamp_ic_pause_white_48dp);
                }
            }
        });

        /*------------------Update SeekBar player---------------------------------------*/
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    seekBar.setProgress(progress);
                    mPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
