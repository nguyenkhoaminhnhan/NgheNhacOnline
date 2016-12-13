package com.example.minhnhan.music.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.minhnhan.music.Adapter.SongListAdapter;
import com.example.minhnhan.music.Model.Album;
import com.example.minhnhan.music.Model.Async.Data.MediaManager;
import com.example.minhnhan.music.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import static com.example.minhnhan.music.Utils.Utils.AddSongListLinearL;

public class AlbumDetailActivity extends AppCompatActivity {

    private Album album;
    private ImageView songImage;
    private TextView plName;
    private TextView plSinger;
    private ImageView preButton;
    private ImageView playButton;
    private ImageView nextButton;
    private LinearLayout plFrame;
    private DisplayImageOptions options;
    private ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_detail);
        TextView detail = (TextView) findViewById(R.id.detail_album_detail);
        ImageView image = (ImageView) findViewById(R.id.detail_album_image);
        RelativeLayout playall = (RelativeLayout) findViewById(R.id.play_all);
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        songImage = (ImageView) findViewById(R.id.dt_ab_pl_image);
        plName = (TextView) findViewById(R.id.dt_ab_pl_song_name);
        plSinger = (TextView) findViewById(R.id.dt_ab_pl_singer_name);
        playButton = (ImageView) findViewById(R.id.dt_ab_pl_play_pause);
        plFrame = (LinearLayout) findViewById(R.id.dt_ab_pl_frame);
        preButton = (ImageView) findViewById(R.id.dt_ab_pl_prev);
        nextButton = (ImageView) findViewById(R.id.dt_ab_pl_next);

        if (MediaManager.getInstance().getmPlayer().isPlaying()) {
            updatePlayBack();
        }
        playall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaManager.getInstance().setPrepareToPlay(0);
                Intent i = new Intent(AlbumDetailActivity.this, FullScreenPlayActivity.class);
                AlbumDetailActivity.this.startActivityForResult(i, 11);
            }
        });

        album = MediaManager.getInstance().getAlbum();
        this.setTitle(album.name);

        detail.setText(album.detail);
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_stub)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.default_image).cacheInMemory(true)
                .cacheOnDisk(true).considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565).build();

        final ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        imageLoader.displayImage(album.getImagePath(), image, options, null);
        /*-------------------Song List---------------------------*/
        LinearLayout playlist = (LinearLayout) findViewById(R.id.detail_album_content);
        SongListAdapter songListAdapter = new SongListAdapter(this,
                MediaManager.getInstance().getPrepareList());
        AddSongListLinearL(playlist, songListAdapter);

        preButton = (ImageView) findViewById(R.id.dt_ab_pl_prev);
        nextButton = (ImageView) findViewById(R.id.dt_ab_pl_next);

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
            updatePlayBack();
            MediaManager.getInstance().setPlayListener(listener);
            MediaManager.getInstance().setPlayCompleteListener(playCompleteListener);
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
