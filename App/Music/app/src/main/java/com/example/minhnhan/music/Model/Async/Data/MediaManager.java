package com.example.minhnhan.music.Model.Async.Data;


import android.media.MediaPlayer;
import android.util.Log;

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

    public boolean isPlayed;

    public interface IPlayListener {
        void onPlay(int currentPlayID);
    }

    public interface IPlayCompleteListener {
        void onPlayComplete();
    }

    private IPlayListener playListener;
    private IPlayCompleteListener playCompleteListener;

    public void setPlayListener(IPlayListener listener) {
        playListener = listener;
    }

    public void setPlayCompleteListener(IPlayCompleteListener listener) {
        playCompleteListener = listener;
    }

    private static MediaManager instance;
    private MediaPlayer mPlayer;
    private int currentPlayID = 0;
    private Album Album;
    private Song playingSong;
    private ArrayList<Song> playList = new ArrayList<>();
    private ArrayList<Song> prepareList = new ArrayList<>();

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
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (playCompleteListener != null)
                    playCompleteListener.onPlayComplete();
                else {
                    next();
                }
            }
        });
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
        return getPlayList() == null ? 0 : getPlayList().size();
    }

    public void setPlayList(ArrayList<Song> playList) {
        this.playList = playList;
        currentPlayID = 0;
        playingSong = playList.get(0);
    }

    public void playAgain() {
        currentPlayID = 0;
        playingSong = getPlayList().get(0);
        play();
    }

    public void resetCurrent() {
        currentPlayID = 0;
        playingSong = getPlayList().get(0);
        play();
    }

    public void setOnSongToPlay(Song data) {
        getPlayList().clear();
        getPlayList().add(data);
        currentPlayID = 0;
        playingSong = getPlayList().get(0);
    }

    public boolean prev() {
        if (getPlayList().size() == 1)
            return false;
        if (currentPlayID > 0) {
            currentPlayID--;
            playingSong = getPlayList().get(currentPlayID);
            play();
            return true;
        } else {
            currentPlayID = getPlayList().size() - 1;
            playingSong = getPlayList().get(currentPlayID);
            play();
            return true;
        }
    }

    public boolean next() {
        if (getPlayList().size() == 1)
            return false;
        if (currentPlayID < getPlayList().size() - 1) {
            currentPlayID++;
            playingSong = getPlayList().get(currentPlayID);
            play();
            return true;
        } else {
            currentPlayID = 0;
            playingSong = getPlayList().get(currentPlayID);
            play();
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
            mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mPlayer.start();
                    isPlayed = true;
                    if (playListener != null) {
                        playListener.onPlay(currentPlayID);
                    }
                }
            });
            mPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AsyncSongListen asyncSongListen = new AsyncSongListen(null);
        asyncSongListen.execute(UPDATE_LISTEN + playingSong.getId());
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
}
