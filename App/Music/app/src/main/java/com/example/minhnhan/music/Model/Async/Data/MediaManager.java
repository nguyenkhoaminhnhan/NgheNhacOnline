package com.example.minhnhan.music.Model.Async.Data;


import android.media.MediaPlayer;

import com.example.minhnhan.music.Model.Song;

import java.util.ArrayList;

/**
 * Created by MinhNhan on 21/11/2016.
 * Restore all data off program from server
 */
public class MediaManager {
    private static MediaManager instance;
    private MediaPlayer mPlayer;
    private int currentPlayID;
    private Song playingSong;
    private ArrayList<Song> playList = new ArrayList<>();


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

    public void setPlayList(ArrayList<Song> playList) {
        this.playList = playList;
    }

    public void setOnSongToPlay(Song data) {
        playList.clear();
        playList.add(data);
    }
}
