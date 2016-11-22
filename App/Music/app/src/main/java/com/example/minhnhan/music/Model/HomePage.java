package com.example.minhnhan.music.Model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Minh Nhan on 11/21/2016.
 */

public class HomePage {
    public final String SHOW_CASE ="showCase";
    public final String HOT_SONG ="hotSong";
    public final String HOT_SONG_WEEK ="hotSongWeek";
    public final String HOT_SONG_MONTH ="hotSongMonth";

    private ArrayList<Song> showCase;
    private ArrayList<Song> hotSong;
    private ArrayList<Song> hotSongWeek;
    private ArrayList<Song> hotSongMonth;

    public HomePage(JSONObject object) {
        try {
            if(object.has(SHOW_CASE))
            {
                String showCaseData = object.getString(SHOW_CASE);
                JSONArray jsonArray = new JSONArray(showCaseData);
                ArrayList<Song> results = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    Song song = new Song(jsonArray.getJSONObject(i));
                    results.add(song);
                }
                setShowCase(results);
            }
            if(object.has(HOT_SONG))
            {
                String showCaseData = object.getString(HOT_SONG);
                JSONArray jsonArray = new JSONArray(showCaseData);
                ArrayList<Song> results = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    Song song = new Song(jsonArray.getJSONObject(i));
                    results.add(song);
                }
                setHotSong(results);
            }
            if(object.has(HOT_SONG_WEEK))
            {
                String showCaseData = object.getString(HOT_SONG_WEEK);
                JSONArray jsonArray = new JSONArray(showCaseData);
                ArrayList<Song> results = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    Song song = new Song(jsonArray.getJSONObject(i));
                    results.add(song);
                }
                setHotSongWeek(results);
            }
            if(object.has(HOT_SONG_MONTH))
            {
                String showCaseData = object.getString(HOT_SONG_MONTH);
                JSONArray jsonArray = new JSONArray(showCaseData);
                ArrayList<Song> results = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    Song song = new Song(jsonArray.getJSONObject(i));
                    results.add(song);
                }
                setHotSongMonth(results);
            }
        } catch (Exception e) {
        }
    }

    public ArrayList<Song> getHotSong() {
        return hotSong;
    }

    public void setHotSong(ArrayList<Song> hotSong) {
        this.hotSong = hotSong;
    }

    public ArrayList<Song> getHotSongWeek() {
        return hotSongWeek;
    }

    public void setHotSongWeek(ArrayList<Song> hotSongWeek) {
        this.hotSongWeek = hotSongWeek;
    }

    public ArrayList<Song> getHotSongMonth() {
        return hotSongMonth;
    }

    public void setHotSongMonth(ArrayList<Song> hotSongMonth) {
        this.hotSongMonth = hotSongMonth;
    }

    public ArrayList<Song> getShowCase() {
        return showCase;
    }

    public void setShowCase(ArrayList<Song> showCase) {
        this.showCase = showCase;
    }
}
