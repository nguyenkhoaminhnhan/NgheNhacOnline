package com.example.minhnhan.music.Model.Async.Data;


import android.media.MediaPlayer;

import com.example.minhnhan.music.Activity.AlbumDetailActivity;
import com.example.minhnhan.music.Activity.HomeActivity;
import com.example.minhnhan.music.Activity.MoreAlbumActivity;
import com.example.minhnhan.music.Adapter.MyPlayListSlideAdapter;
import com.example.minhnhan.music.Model.Album;
import com.example.minhnhan.music.Model.Async.AsyncListener;
import com.example.minhnhan.music.Model.Async.AsyncSongListen;
import com.example.minhnhan.music.Model.Song;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.minhnhan.music.Utils.Constants.UPDATE_LISTEN;

/**
 * Created by MinhNhan on 21/11/2016.
 * Restore all data off program from server
 */
public class MediaManager {
    private static MediaManager instance;
    private MediaPlayer mPlayer;
    private int currentPlayID = 0;
    private Album Album;
    private Song playingSong;
    private ArrayList<Song> playList = new ArrayList<>();
    private ArrayList<Song> prepareList = new ArrayList<>();

    private MoreAlbumActivity moreAlbumActivity;
    private HomeActivity homeActivity;
    private AlbumDetailActivity albumDetailActivity;
    private MyPlayListSlideAdapter playlistSlide;

    public static MediaManager getInstance() {
        if (instance == null)
            instance = new MediaManager();
        return instance;
    }

    private MediaManager() {
        setmPlayer(new MediaPlayer());
    }


    public MediaPlayer getmPlayer() {
        return mPlayer;
    }

    public void setmPlayer(MediaPlayer mPlayer) {
        this.mPlayer = mPlayer;
    }

    public int getCurrentPlayID() {
        return currentPlayID;
    }

    public void setCurrentPlayID(int currentPlayID) {
        this.currentPlayID = currentPlayID;
    }

    public Song getPlayingSong() {
        return playingSong;
    }

    public void setPlayingSong(Song playingSong) {
        this.playingSong = playingSong;
    }

    public ArrayList<Song> getPlayList() {
        return playList;
    }

    public int getDataSize() {
        return playList == null ? 0 : playList.size();
    }

    public void setPlayList(ArrayList<Song> playList) {
        this.playList = playList;
        currentPlayID = 0;
        playingSong = playList.get(0);
    }

    public void playAgain() {
        currentPlayID = 0;
        playingSong = playList.get(0);
        play();
    }

    public void resetCurrent() {
        currentPlayID = 0;
        playingSong = playList.get(0);
        play();
    }

    public void setOnSongToPlay(Song data) {
        playList.clear();
        playList.add(data);
        currentPlayID = 0;
        playingSong = playList.get(0);
    }

    public boolean prev() {
        if (playList.size() == 1)
            return false;
        if (currentPlayID > 0) {
            currentPlayID--;
            playingSong = playList.get(currentPlayID);
            play();
            return true;
        } else {
            currentPlayID = playList.size() - 1;
            playingSong = playList.get(currentPlayID);
            play();
            return true;
        }
    }

    public boolean next() {
        if (playList.size() == 1)
            return false;
        if (currentPlayID < playList.size() - 1) {
            currentPlayID++;
            playingSong = playList.get(currentPlayID);
            play();
            mPlayer.start();
            return true;
        } else {
            currentPlayID = 0;
            playingSong = playList.get(currentPlayID);
            play();
            mPlayer.start();
            return true;
        }
    }

    public void pause() {
        if (!mPlayer.isPlaying())
            mPlayer.start();
        else if (mPlayer.isPlaying())
            mPlayer.pause();
    }

    public void play() {
        if (mPlayer != null) {
            try {
                //mPlayer.release();
                mPlayer.reset();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            mPlayer.setDataSource(playingSong.getSourcePath());
            mPlayer.prepare();
            mPlayer.start();
            AsyncSongListen asyncSongListen = new AsyncSongListen(new AsyncListener() {
                @Override
                public void onAsyncComplete() {

                }
            });
            asyncSongListen.execute(UPDATE_LISTEN + playingSong.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (moreAlbumActivity != null)
            moreAlbumActivity.updatePlayBack();
        else if (homeActivity != null)
            homeActivity.updatePlayBack();
        else if (albumDetailActivity != null)
            albumDetailActivity.updatePlayBack();
        else if (playlistSlide != null)
            playlistSlide.update();
    }

    public Album getAlbum() {
        return Album;
    }

    public void setAlbum(Album playingAlbum) {
        this.Album = playingAlbum;
    }

    public ArrayList<Song> getPrepareList() {
        return prepareList;
    }

    public void setPrepareList(ArrayList<Song> prepareList) {
        this.prepareList = prepareList;
    }

    public void setPrepareToPlay(int startID) {
        setPlayList(prepareList);
        setPlayingSong(prepareList.get(startID));
        currentPlayID = startID;
    }

    public MoreAlbumActivity getMoreAlbumActivity() {
        return moreAlbumActivity;
    }

    public void setMoreAlbumActivity(MoreAlbumActivity moreAlbumActivity) {
        this.moreAlbumActivity = moreAlbumActivity;
    }

    public HomeActivity getHomeActivity() {
        return homeActivity;
    }

    public void setHomeActivity(HomeActivity homeActivity) {
        this.homeActivity = homeActivity;
    }

    public AlbumDetailActivity getAlbumDetailActivity() {
        return albumDetailActivity;
    }

    public void setAlbumDetailActivity(AlbumDetailActivity albumDetailActivity) {
        this.albumDetailActivity = albumDetailActivity;
    }

    public MyPlayListSlideAdapter getPlaylistSlide() {
        return playlistSlide;
    }

    public void setPlaylistSlide(MyPlayListSlideAdapter playlistSlide) {
        this.playlistSlide = playlistSlide;
    }
}
