package com.example.minhnhan.music.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.minhnhan.music.Activity.FullScreenPlayActivity;
import com.example.minhnhan.music.Activity.HomeActivity;
import com.example.minhnhan.music.Model.Async.Data.MediaManager;
import com.example.minhnhan.music.Model.Song;
import com.example.minhnhan.music.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

/**
 * Created by Minh Nhan on 11/27/2016.
 */

public class PlayListAdapter extends BaseAdapter {
    public ArrayList<Song> data;
    private LayoutInflater mInflater;
    private DisplayImageOptions options;
    private FullScreenPlayActivity activity;
    private Song item;
    private int oldPlayPositon;
    private ArrayList<ViewHolder> listHolder;

    public PlayListAdapter(FullScreenPlayActivity activity, ArrayList<Song> data) {
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
        listHolder = new ArrayList<>();
        oldPlayPositon = MediaManager.getInstance().getCurrentPlayID();
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
        final ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.play_song_row, parent, false);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.song_name);
            holder.singer = (TextView) convertView.findViewById(R.id.singer);
            holder.image = (ImageView) convertView.findViewById(R.id.song_image);

            holder.animation = (ImageView) convertView.findViewById(R.id.playing_animation);
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
        holder.animation.setImageResource(R.drawable.uamp_ic_play_arrow_white_48dp);
        listHolder.add(holder);
        if (MediaManager.getInstance().getCurrentPlayID() == position) {
            updatePlaying(position);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaManager.getInstance().setCurrentPlayID(position);
                MediaManager.getInstance().setPlayingSong(data.get(position));
                MediaManager.getInstance().play();
                activity.newMediaPlayer();
                updatePlaying(position);

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
        private ImageView animation;

    }

    public void setdata(ArrayList<Song> temp) {
        data = temp;
        notifyDataSetChanged();
    }

    public String getImageUrl() {
        return item.getImagePath();
    }

    public void updatePlaying(int position) {
        listHolder.get(oldPlayPositon).animation.setImageResource(R.drawable.uamp_ic_play_arrow_white_48dp);

        AnimationDrawable animation = (AnimationDrawable)
                ContextCompat.getDrawable(activity, R.drawable.animation);
        ColorStateList sColorStatePlaying = ColorStateList.valueOf(activity.getResources().getColor(
                R.color.colorAccent));
        DrawableCompat.setTintList(animation, sColorStatePlaying);
        listHolder.get(position).animation.setImageDrawable(animation);
        animation.start();
        oldPlayPositon = position;
    }
}
