package com.example.minhnhan.music.Activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.minhnhan.music.Adapter.MyPlayListSlideAdapter;
import com.example.minhnhan.music.Model.Async.Data.MediaManager;
import com.example.minhnhan.music.R;
import com.example.minhnhan.music.Utils.DepthPageTransformer;
import com.viewpagerindicator.CirclePageIndicator;

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

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 1) {
                MediaManager.getInstance().seekBarListener.seekBarUpdater();
            }
            return false;
        }
    });

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
        formatter = new SimpleDateFormat("mm:ss");

        /*---------------------set adapter------------------------------------*/
        FragmentManager manager = getSupportFragmentManager();
        playlist = new MyPlayListSlideAdapter(manager,
                MediaManager.getInstance().getPlayList(),
                MediaManager.getInstance().getPlayingSong());
        /*---------------------set pager--------------------------------------*/
        ViewPager bodyPlay = (ViewPager) findViewById(R.id.body_play);
        bodyPlay.setPageTransformer(true, new DepthPageTransformer());


        bodyPlay.setAdapter(playlist);
        //Bind the title indicator to the adapter
        CirclePageIndicator indicator = (CirclePageIndicator) findViewById(R.id.page_indicator);
        indicator.setViewPager(bodyPlay);

        mPlayer = MediaManager.getInstance().getmPlayer();
        if (MediaManager.getInstance().isContinue) {
            newMediaPlayer();
            MediaManager.getInstance().setPlayListener(listener);
            MediaManager.getInstance().continuePlay();
            MediaManager.getInstance().isContinue = false;
        } else {
            MediaManager.getInstance().play();

            MediaManager.getInstance().setPlayListener(listener);
        }

        MediaManager.getInstance().setPlayCompleteListener(playCompleteListener);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MediaManager.getInstance().next()) {
                    Log.d("debug", "----- Next Button ----- ");
                    newMediaPlayer();
                    playlist.update();
                }
            }
        });
        preButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MediaManager.getInstance().prev()) {
                    Log.d("debug", "----- Pre Button ----- ");
                    newMediaPlayer();
                    playlist.update();
                }
            }
        });
    }

    MediaManager.IPlayListener listener = new MediaManager.IPlayListener() {
        @Override
        public void onPlay(final int currentPlayID) {
            newMediaPlayer();
            mediaFileLength = mPlayer.getDuration();
            seekBar.setMax(mediaFileLength);
            endTime.setText(formatter.format(mediaFileLength));
            MediaManager.getInstance().seekBarListener = seekBarListener;
            seekBarListener.seekBarUpdater();
        }
    };

    MediaManager.IPlayCompleteListener playCompleteListener = new MediaManager.IPlayCompleteListener() {
        @Override
        public void onPlayComplete() {
            if (MediaManager.getInstance().next()) {
                Log.d("debug", "----- Play Complete ----- ");
                playlist.update();
            }
        }
    };

    private MediaManager.ISeekBarListener seekBarListener = new MediaManager.ISeekBarListener() {
        @Override
        public void seekBarUpdater() {
            currentTime = MediaManager.getInstance().getmPlayer().getCurrentPosition();
            realTime.setText(formatter.format(currentTime));
            seekBar.setProgress(currentTime);
            if (MediaManager.getInstance().getmPlayer().isPlaying()) {
                handler.sendEmptyMessageDelayed(1, 500);
            } else
                handler.removeMessages(1);
        }
    };

    /*private void seekBarProgressUpdater() {
            currentTime = MediaManager.getInstance().getmPlayer().getCurrentPosition();
            realTime.setText(formatter.format(currentTime));
            seekBar.setProgress(currentTime);
            if (MediaManager.getInstance().getmPlayer().isPlaying()) {
                handler.sendEmptyMessageDelayed(1, 500);
            } else
                handler.removeMessages(1);
    }*/

    public void newMediaPlayer() {
        Log.d("debug", "----- newMediaPlayer ----- ");
        playButton.setImageResource(R.drawable.uamp_ic_pause_white_48dp);
        songName.setText(MediaManager.getInstance().getPlayingSong().name);
        singerName.setText(MediaManager.getInstance().getPlayingSong().singer);

        //MediaManager.getInstance().setPlayListener(listener);

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
                    MediaManager.getInstance().seekBarListener.seekBarUpdater();
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
                    playButton.setImageResource(R.drawable.uamp_ic_pause_white_48dp);
                    mPlayer.start();
                    MediaManager.getInstance().seekBarListener.seekBarUpdater();
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
