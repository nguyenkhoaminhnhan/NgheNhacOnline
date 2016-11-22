package com.example.minhnhan.music.Model.Async;

/**
 * Created by MinhNhan on 27/04/2016.
 * post request to server to get AtmDetail Json
 */

import android.os.AsyncTask;

import com.example.minhnhan.music.Model.Async.Data.DataManager;
import com.example.minhnhan.music.Model.Async.Data.DataServices;
import com.example.minhnhan.music.Model.HomePage;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class AsyncHomePage extends AsyncTask<String, String, String> {
    private AsyncListener listener;

    public AsyncHomePage(AsyncListener listener) {
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
            DataManager.getInstance().setHomeDetail(new HomePage(object));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (listener != null) {
            listener.onAsyncComplete();
        }
    }
}