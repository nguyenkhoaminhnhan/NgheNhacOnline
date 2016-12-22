package com.example.minhnhan.music.Model.Async;

/**
 * Created by MinhNhan on 27/04/2016.
 * post request to server to get AtmDetail Json
 */

import android.os.AsyncTask;

import com.example.minhnhan.music.Model.Async.AsyncListener;
import com.example.minhnhan.music.Model.Async.Data.DataManager;
import com.example.minhnhan.music.Model.Async.Data.DataServices;
import com.example.minhnhan.music.Model.Song;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class AsyncSearchSong extends AsyncTask<String, String, String> {
    private AsyncListener listener;

    public AsyncSearchSong(AsyncListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            return DataServices.getRequest(params[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        try {
            JSONObject object = new JSONObject(result);
            if (object.has("Song")) {
                String songData = object.getString("Song");
                JSONArray jsonArrayTemp = new JSONArray(songData);
                String temp = jsonArrayTemp.getString(0);
                JSONArray jsonArray = new JSONArray(temp);

                ArrayList<Song> results = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    Song song = new Song(jsonArray.getJSONObject(i));
                    results.add(song);
                }
                DataManager.getInstance().setSong(results);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (listener != null) {
            listener.onAsyncComplete();
        }
    }
}