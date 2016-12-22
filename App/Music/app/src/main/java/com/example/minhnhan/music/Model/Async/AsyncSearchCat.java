package com.example.minhnhan.music.Model.Async;

/**
 * Created by MinhNhan on 27/04/2016.
 * post request to server to get AtmDetail Json
 */

import android.os.AsyncTask;

import com.example.minhnhan.music.Model.Async.AsyncListener;
import com.example.minhnhan.music.Model.Async.Data.DataManager;
import com.example.minhnhan.music.Model.Async.Data.DataServices;
import com.example.minhnhan.music.Model.Category;
import com.example.minhnhan.music.Model.Song;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class AsyncSearchCat extends AsyncTask<String, String, String> {
    private AsyncListener listener;

    public AsyncSearchCat(AsyncListener listener) {
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
            if (object.has("Cat")) {
                String catData = object.getString("Cat");
                JSONArray jsonArrayTemp = new JSONArray(catData);
                String temp = jsonArrayTemp.getString(0);
                JSONArray jsonArray = new JSONArray(temp);

                ArrayList<Category> results = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    Category cat = new Category(jsonArray.getJSONObject(i));
                    results.add(cat);
                }
                DataManager.getInstance().setCategory(results);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (listener != null) {
            listener.onAsyncComplete();
        }
    }
}