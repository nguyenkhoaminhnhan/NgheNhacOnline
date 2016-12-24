package com.example.minhnhan.music.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.minhnhan.music.Fragment.AlbumFragment;
import com.example.minhnhan.music.Fragment.CategoryFragment;
import com.example.minhnhan.music.Fragment.HomeFragment;
import com.example.minhnhan.music.Fragment.SingerFragment;
import com.example.minhnhan.music.Model.Async.AsyncListener;
import com.example.minhnhan.music.Model.Async.AsyncSearchPage;
import com.example.minhnhan.music.Model.Async.Data.DataManager;
import com.example.minhnhan.music.Model.Async.Data.MediaManager;
import com.example.minhnhan.music.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static com.example.minhnhan.music.Utils.Constants.GET_SEARCH_ALL;
import static com.example.minhnhan.music.Utils.Utils.hideSoftKeyboard;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    HomeFragment homeFragment;
    private int idPage;

    boolean isSearch;
    String titleNow = "Trang Chủ";

    private ImageView songImage;
    private TextView plName;
    private TextView plSinger;
    private ImageView preButton;
    private ImageView playButton;
    private ImageView nextButton;
    private RelativeLayout plFrame;
    private DisplayImageOptions options;
    private ImageLoader imageLoader;

    private EditText searchTXT;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        progress = new ProgressDialog(this);
        progress.setTitle("Vui lòng chờ");
        progress.setMessage("Đang tải...");
        progress.setCancelable(false);

        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        songImage = (ImageView) findViewById(R.id.pl_image);
        plName = (TextView) findViewById(R.id.pl_song_name);
        plSinger = (TextView) findViewById(R.id.pl_singer_name);
        playButton = (ImageView) findViewById(R.id.pl_play_pause);
        plFrame = (RelativeLayout) findViewById(R.id.pl_frame);
        searchTXT = (EditText) findViewById(R.id.search_txt);

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_stub)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.default_image).cacheInMemory(true)
                .cacheOnDisk(true).considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565).build();

        preButton = (ImageView) findViewById(R.id.pl_prev);
        nextButton = (ImageView) findViewById(R.id.pl_next);

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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        homeFragment = new HomeFragment();

        fragmentManager.beginTransaction()
                .replace(R.id.content_home, homeFragment)
                .commit();
        this.setTitle("Trang Chủ");

        searchTXT = (EditText) findViewById(R.id.search_txt);
        ImageButton searchBtn = (ImageButton) findViewById(R.id.home_search);

        searchTXT.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    switch (i) {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            if (!searchTXT.getText().toString().equals("")) {
                                progress.show();
                                String query = searchTXT.getText().toString();
                                try {
                                    query = URLEncoder.encode(query, "utf-8");
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                                AsyncSearchPage asyncSearchPage = new AsyncSearchPage(new AsyncListener() {
                                    @Override
                                    public void onAsyncComplete() {
                                        Intent i = new Intent(HomeActivity.this, SearchActivity.class);
                                        searchTXT.setText("");
                                        isSearch = false;
                                        searchTXT.setVisibility(View.INVISIBLE);
                                        HomeActivity.this.setTitle(titleNow);
                                        HomeActivity.this.startActivityForResult(i, 11);
                                        progress.dismiss();
                                    }
                                });
                                DataManager.getInstance().setSearchTXT(query);
                                asyncSearchPage.execute(String.format(GET_SEARCH_ALL, query, "all", 0));
                            }
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSearch == true) {
                    if (searchTXT.getText().toString().equals("")) {
                        isSearch = false;
                        searchTXT.setVisibility(View.INVISIBLE);
                        HomeActivity.this.setTitle(titleNow);
                        hideSoftKeyboard(HomeActivity.this);
                    } else if (!searchTXT.getText().toString().equals("")) {
                        progress.show();
                        String query = searchTXT.getText().toString();
                        try {
                            query = URLEncoder.encode(query, "utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        AsyncSearchPage asyncSearchPage = new AsyncSearchPage(new AsyncListener() {
                            @Override
                            public void onAsyncComplete() {
                                Intent i = new Intent(HomeActivity.this, SearchActivity.class);
                                isSearch = false;
                                Bundle b = new Bundle();
                                b.putString("search", searchTXT.getText().toString());
                                i.putExtras(b);
                                searchTXT.setText("");
                                searchTXT.setVisibility(View.INVISIBLE);
                                HomeActivity.this.setTitle(titleNow);
                                HomeActivity.this.startActivityForResult(i, 11);
                                progress.dismiss();
                            }
                        });
                        DataManager.getInstance().setSearchTXT(query);
                        asyncSearchPage.execute(String.format(GET_SEARCH_ALL, query, "all", 0));
                    }
                } else {
                    isSearch = true;
                    searchTXT.setVisibility(View.VISIBLE);
                    HomeActivity.this.setTitle("");
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        try {
            MediaManager.getInstance().seekBarListener = new MediaManager.ISeekBarListener() {
                @Override
                public void seekBarUpdater() {

                }
            };
            if (MediaManager.getInstance().isPlayed) {
                MediaManager.getInstance().getmPlayer().stop();
            }
        } catch (Exception e) {
        }

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(HomeActivity.this, AboutActivity.class);
            startActivityForResult(i, 11);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (idPage == id) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }

        switch (id) {
            case R.id.nav_home:
                homeFragment = new HomeFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_home, homeFragment)
                        .commit();
                titleNow = "Trang Chủ";
                if (!isSearch) {
                    this.setTitle(titleNow);
                }
                break;
            case R.id.nav_category:
                CategoryFragment categoryFragment = new CategoryFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_home, categoryFragment)
                        .commit();
                titleNow = "Thể Loại";
                if (!isSearch) {
                    this.setTitle(titleNow);
                }
                break;
            case R.id.nav_singer:
                SingerFragment singerFragment = new SingerFragment(HomeActivity.this);
                fragmentManager.beginTransaction()
                        .replace(R.id.content_home, singerFragment)
                        .commit();
                titleNow = "Ca Sĩ";
                if (!isSearch) {
                    this.setTitle(titleNow);
                }
                break;
            case R.id.nav_album:
                AlbumFragment albumFragment = new AlbumFragment(HomeActivity.this);
                fragmentManager.beginTransaction()
                        .replace(R.id.content_home, albumFragment)
                        .commit();
                titleNow = "Album";
                if (!isSearch) {
                    this.setTitle(titleNow);
                }
                break;
            /*case R.id.nav_share:
                break;
            case R.id.nav_send:
                break;*/
        }

        idPage = id;
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
                LinearLayout plInfo = (LinearLayout) findViewById(R.id.pl_info);
                plInfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MediaManager.getInstance().isContinue = true;
                        Intent i = new Intent(HomeActivity.this, FullScreenPlayActivity.class);
                        HomeActivity.this.startActivityForResult(i, 11);
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