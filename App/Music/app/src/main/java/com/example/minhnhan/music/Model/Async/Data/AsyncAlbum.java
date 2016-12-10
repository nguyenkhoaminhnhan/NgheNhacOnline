package com.example.minhnhan.music.Model.Async.Data;

/**
 * Created by MinhNhan on 27/04/2016.
 * post request to server to get AtmDetail Json
 */

import android.os.AsyncTask;

import com.example.minhnhan.music.Model.Album;
import com.example.minhnhan.music.Model.Async.AsyncListener;
import com.example.minhnhan.music.Model.Song;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class AsyncAlbum extends AsyncTask<String, String, String> {
    private AsyncListener listener;

    public AsyncAlbum(AsyncListener listener) {
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
            JSONArray objectList = new JSONArray(result);
            ArrayList<Album> album = new ArrayList<>();
            for (int i = 0; i < objectList.length(); i++) {
                album.add(new Album(objectList.getJSONObject(i)));
            }
            DataManager.getInstance().setMoreAlbum(album);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (listener != null) {
            listener.onAsyncComplete();
        }
    }
}