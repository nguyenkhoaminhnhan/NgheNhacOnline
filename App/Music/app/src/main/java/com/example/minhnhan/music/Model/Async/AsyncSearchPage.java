package com.example.minhnhan.music.Model.Async;

/**
 * Created by MinhNhan on 27/04/2016.
 * post request to server to get AtmDetail Json
 */

import android.os.AsyncTask;

import com.example.minhnhan.music.Model.Album;
import com.example.minhnhan.music.Model.Async.Data.DataManager;
import com.example.minhnhan.music.Model.Async.Data.DataServices;
import com.example.minhnhan.music.Model.Category;
import com.example.minhnhan.music.Model.Page.HomePage;
import com.example.minhnhan.music.Model.Singer;
import com.example.minhnhan.music.Model.Song;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class AsyncSearchPage extends AsyncTask<String, String, String> {
    private AsyncListener listener;

    public AsyncSearchPage(AsyncListener listener) {
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
            if(object.has("Song"))
            {
                String songData = object.getString("Song");
                JSONArray jsonArray = new JSONArray(songData);
                ArrayList<Song> results = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    Song song = new Song(jsonArray.getJSONObject(i));
                    results.add(song);
                }
                DataManager.getInstance().setSong(results);
            }
            if(object.has("Singer"))
            {
                String singerData = object.getString("Singer");
                JSONArray jsonArray = new JSONArray(singerData);
                ArrayList<Singer> results = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    Singer singer = new Singer(jsonArray.getJSONObject(i));
                    results.add(singer);
                }
                DataManager.getInstance().setSinger(results);
            }if(object.has("Cat"))
            {
                String catData = object.getString("Cat");
                JSONArray jsonArray = new JSONArray(catData);
                ArrayList<Category> results = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    Category category = new Category(jsonArray.getJSONObject(i));
                    results.add(category);
                }
                DataManager.getInstance().setCategory(results);
            }if(object.has("Album"))
            {
                String albumData = object.getString("Album");
                JSONArray jsonArray = new JSONArray(albumData);
                ArrayList<Album> results = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    Album album = new Album(jsonArray.getJSONObject(i));
                    results.add(album);
                }
                DataManager.getInstance().setAlbum(results);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (listener != null) {
            listener.onAsyncComplete();
        }
    }
}