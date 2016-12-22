package com.example.minhnhan.music.Model.Async;

/**
 * Created by MinhNhan on 27/04/2016.
 * post request to server to get AtmDetail Json
 */

import android.os.AsyncTask;

import com.example.minhnhan.music.Model.Async.Data.DataManager;
import com.example.minhnhan.music.Model.Async.Data.DataServices;
import com.example.minhnhan.music.Model.Singer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class AsyncSearchSinger extends AsyncTask<String, String, String> {
    private AsyncListener listener;

    public AsyncSearchSinger(AsyncListener listener) {
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
            if (object.has("Singer")) {
                String singerData = object.getString("Singer");
                JSONArray jsonArrayTemp = new JSONArray(singerData);
                String temp = jsonArrayTemp.getString(0);
                JSONArray jsonArray = new JSONArray(temp);

                ArrayList<Singer> results = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    Singer singer = new Singer(jsonArray.getJSONObject(i));
                    results.add(singer);
                }
                DataManager.getInstance().setSinger(results);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (listener != null) {
            listener.onAsyncComplete();
        }
    }
}