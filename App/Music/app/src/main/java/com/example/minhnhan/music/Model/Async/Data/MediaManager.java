package com.example.minhnhan.music.Model.Async.Data;


import android.media.MediaPlayer;

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
    private Album playingAlbum;
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

    public int getDataSize(){return playList == null ? 0 : playList.size(); }
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
    public void resetCurrent(){
        currentPlayID = 0;
        playingSong = playList.get(0);
    }

    public void setOnSongToPlay(Song data) {
        playList.clear();
        playList.add(data);
        currentPlayID = 0;
        playingSong = playList.get(0);
    }

    public void prev() {
        if (currentPlayID > 0) {
            currentPlayID--;
            playingSong = playList.get(currentPlayID);
            play();
        }
    }

    public void next() {
        if (currentPlayID < playList.size() - 1) {
            currentPlayID++;
            playingSong = playList.get(currentPlayID);
            play();
            mPlayer.start();
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
    }

    public Album getPlayingAlbum() {
        return playingAlbum;
    }

    public void setPlayingAlbum(Album playingAlbum) {
        this.playingAlbum = playingAlbum;
    }
}
