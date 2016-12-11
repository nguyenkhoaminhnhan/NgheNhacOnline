package com.example.minhnhan.music.Activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

    private DisplayImageOptions options;
    private Album album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_detail);
        TextView detail = (TextView) findViewById(R.id.detail_album_detail);
        ImageView image = (ImageView) findViewById(R.id.detail_album_image);

        album = MediaManager.getInstance().getPlayingAlbum();
        this.setTitle(album.name);

        detail.setText(album.detail);
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_stub)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.default_image).cacheInMemory(true)
                .cacheOnDisk(true).considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565).build();

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        imageLoader.displayImage(album.getImagePath(), image, options, null);
        /*-------------------Song List---------------------------*/
        LinearLayout playlist = (LinearLayout) findViewById(R.id.detail_album_content);
        SongListAdapter songListAdapter = new SongListAdapter(this,
                MediaManager.getInstance().getPlayList());
        AddSongListLinearL(playlist, songListAdapter);
    }
}
