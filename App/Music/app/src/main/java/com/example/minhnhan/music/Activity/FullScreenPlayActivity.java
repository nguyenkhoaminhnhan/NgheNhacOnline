package com.example.minhnhan.music.Activity;

import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.minhnhan.music.Model.Async.Data.DataManager;
import com.example.minhnhan.music.Model.Song;
import com.example.minhnhan.music.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.IOException;

public class FullScreenPlayActivity extends AppCompatActivity {

    private Song playingSong;
    private TextView songName;
    private TextView singerName;
    private ImageView songImage;
    ImageButton preButton;
    ImageButton playButton;
    ImageButton nextButton;
    SeekBar seekBar;

    private DisplayImageOptions options;

    MediaPlayer mPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_play);
        songName = (TextView) findViewById(R.id.play_song_name);
        singerName = (TextView) findViewById(R.id.play_singer_name);
        playingSong = DataManager.getInstance().getPlaySong();
        songName.setText(playingSong.name);
        singerName.setText(playingSong.singer);

        songImage = (ImageView) findViewById(R.id.play_background_image);

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_stub)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.default_image).cacheInMemory(true)
                .cacheOnDisk(true).considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565).build();

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        imageLoader.displayImage(playingSong.getImagePath(), songImage, options, null);

        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(playingSong.getSourcePath());

            mPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mPlayer.start();
    }
}
