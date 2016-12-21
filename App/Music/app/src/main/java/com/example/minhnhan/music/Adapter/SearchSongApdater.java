package com.example.minhnhan.music.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.minhnhan.music.Activity.FullScreenPlayActivity;
import com.example.minhnhan.music.Model.Async.Data.MediaManager;
import com.example.minhnhan.music.Model.Song;
import com.example.minhnhan.music.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

/**
 * Created by Minh Nhan on 11/29/2016.
 */

public class SearchSongApdater extends BaseAdapter {
    public ArrayList<Song> data;
    private LayoutInflater mInflater;
    private DisplayImageOptions options;
    private Activity activity;
    private Song item;

    public SearchSongApdater(Activity activity, ArrayList<Song> data) {
        mInflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_stub)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.default_image).cacheInMemory(true)
                .cacheOnDisk(true).considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565).build();
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return data == null ? null : data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.s_song_row, parent, false);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.song_name);
            holder.singer = (TextView) convertView.findViewById(R.id.singer);
            holder.image = (ImageView) convertView.findViewById(R.id.song_image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        item = data.get(position);
        holder.name.setText(item.name);
        holder.singer.setText(item.singer);
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(activity));
        imageLoader.displayImage(item.getImagePath(), holder.image, options, null);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaManager.getInstance().setPlayList(data);
                MediaManager.getInstance().setCurrentPlayID(position);
                MediaManager.getInstance().setPlayingSong(data.get(position));
                Intent i = new Intent(activity, FullScreenPlayActivity.class);
                activity.startActivityForResult(i, 11);
            }
        });
        return convertView;
    }

    public void addMore(ArrayList<Song> data) {
        if (this.data == null)
            this.data = data;
        else
            this.data.addAll(data);
        // yeu cau adapter refresh lai view
        notifyDataSetChanged();
    }

    private class ViewHolder {
        private ImageView image;
        private TextView name;
        private TextView singer;

    }

    public void setdata(ArrayList<Song> temp) {
        data = temp;
        notifyDataSetChanged();
    }

    public String getImageUrl() {
        return item.getImagePath();
    }
}
