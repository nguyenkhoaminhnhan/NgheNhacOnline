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

    private final Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 1) {
                seekBarProgressUpdater();
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
        MediaManager.getInstance().play();
        //MediaManager.getInstance().setPlaylistSlide(playlist);
        newMediaPlayer();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MediaManager.getInstance().next()) {
                    newMediaPlayer();
                }
            }
        });
        preButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MediaManager.getInstance().prev()) {
                    newMediaPlayer();
                }
            }
        });
    }

    MediaManager.IPlayListener listener = new MediaManager.IPlayListener() {
        @Override
        public void onPlay(int currentPlayID) {
            mediaFileLength = mPlayer.getDuration();
            seekBar.setMax(mediaFileLength);
            endTime.setText(formatter.format(mediaFileLength));
            seekBarProgressUpdater();
        }
    };

    private void seekBarProgressUpdater() {
        currentTime = mPlayer.getCurrentPosition();
        Log.d("debug", "current time " + currentTime);
        realTime.setText(formatter.format(currentTime));
        seekBar.setProgress(currentTime);
        if (mPlayer.isPlaying()) {
            handler.sendEmptyMessageDelayed(1, 500);
        }
    }

    public void newMediaPlayer() {
        Log.d("debug", "----- newMediaPlayer ----- ");
        playButton.setImageResource(R.drawable.uamp_ic_pause_white_48dp);
        songName.setText(MediaManager.getInstance().getPlayingSong().name);
        singerName.setText(MediaManager.getInstance().getPlayingSong().singer);
        //mediaFileLength = mPlayer.getDuration();
        //seekBar.setMax(mediaFileLength);
        formatter = new SimpleDateFormat("mm:ss");
        //endTime.setText(formatter.format(mediaFileLength));
        MediaManager.getInstance().setPlayListener(listener);

        /*------------------Update play complete---------------------------------------*/
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                /*int currentPlayID = MediaManager.getInstance().getCurrentPlayID();
                if (currentPlayID == MediaManager.getInstance().getDataSize() - 1) {

                    MediaManager.getInstance().resetCurrent();
                    if (FullScreenPlayActivity.this != null) {
                        newMediaPlayer();
                    }
                } else {
                    seekBar.setProgress(0);
                    currentTime = 0;
                    if (FullScreenPlayActivity.this != null) {
                        MediaManager.getInstance().next();
                        newMediaPlayer();
                    } else
                        MediaManager.getInstance().next();
                }*/

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
                    seekBarProgressUpdater();
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
                    seekBarProgressUpdater();
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
