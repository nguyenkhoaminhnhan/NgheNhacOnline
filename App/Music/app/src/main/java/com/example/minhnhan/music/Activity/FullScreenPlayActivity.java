package com.example.minhnhan.music.Activity;

import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.minhnhan.music.Model.Async.Data.DataManager;
import com.example.minhnhan.music.Model.Async.Data.MediaManager;
import com.example.minhnhan.music.Model.Song;
import com.example.minhnhan.music.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class FullScreenPlayActivity extends AppCompatActivity {

    private int currentPlayID = 0;

    private ArrayList<Song> data;
    private Song playingSong;
    private TextView songName;
    private TextView singerName;
    private TextView realTime;
    private TextView endTime;
    private ImageView songImage;
    ImageView preButton;
    ImageView playButton;
    ImageView nextButton;
    SeekBar seekBar;
    private int currentTime;
    private int mediaFileLength;
    private DateFormat formatter;

    private DisplayImageOptions options;

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

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_stub)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.default_image).cacheInMemory(true)
                .cacheOnDisk(true).considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565).build();
        /*---------------------set playing song--------------------------------------*/
        data = MediaManager.getInstance().getPlayList();
        playingSong = data.get(currentPlayID);
        MediaManager.getInstance().setPlayingSong(playingSong);
        newMediaPlayer();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPlayID++;
                if (currentPlayID >= data.size())
                    currentPlayID--;
                else {
                    playingSong = data.get(currentPlayID);
                    MediaManager.getInstance().setPlayingSong(playingSong);
                    newMediaPlayer();
                }
                MediaManager.getInstance().setCurrentPlayID(currentPlayID);
            }
        });
        preButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = MediaManager.getInstance().getPlayList();
                currentPlayID--;
                if (currentPlayID < 0)
                    currentPlayID++;
                else {
                    playingSong = data.get(currentPlayID);
                    MediaManager.getInstance().setPlayingSong(playingSong);
                    newMediaPlayer();
                }
                MediaManager.getInstance().setCurrentPlayID(currentPlayID);
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

    private void killMediaPlayer() {
        if (mPlayer != null) {
            try {
                //mPlayer.release();
                mPlayer.reset();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void newMediaPlayer() {

        songName.setText(playingSong.name);
        singerName.setText(playingSong.singer);

        songImage = (ImageView) findViewById(R.id.play_background_image);

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        imageLoader.displayImage(playingSong.getImagePath(), songImage, options, null);

        mPlayer = MediaManager.getInstance().getmPlayer();
        killMediaPlayer();
        try {
            mPlayer.setDataSource(playingSong.getSourcePath());
            mPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaFileLength = mPlayer.getDuration();
        seekBar.setMax(mediaFileLength);
        formatter = new SimpleDateFormat("mm:ss");
        endTime.setText(formatter.format(mediaFileLength));

        /*------------------Update play complete---------------------------------------*/
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                playButton.setImageResource(R.drawable.uamp_ic_play_arrow_white_48dp);
                seekBar.setProgress(0);
                currentTime = 0;
                realTime.setText(formatter.format(currentTime));
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
                SeekBarProgressUpdater();
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
        playButton.performClick();
    }
}