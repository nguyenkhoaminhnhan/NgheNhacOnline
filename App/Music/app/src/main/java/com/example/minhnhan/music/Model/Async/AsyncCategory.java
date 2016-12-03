package com.example.minhnhan.music.Model.Async;

/**
 * Created by Minh Nhan on 11/29/2016.
 */

import android.os.AsyncTask;

import com.example.minhnhan.music.Model.Async.Data.DataManager;
import com.example.minhnhan.music.Model.Async.Data.DataServices;
import com.example.minhnhan.music.Model.Page.CategoryPage;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class AsyncCategory extends AsyncTask<String, String, String> {
    private AsyncListener listener;

    public AsyncCategory(AsyncListener listener) {
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
            DataManager.getInstance().setCategoryPage(new CategoryPage(object));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (listener != null) {
            listener.onAsyncComplete();
        }
    }
}