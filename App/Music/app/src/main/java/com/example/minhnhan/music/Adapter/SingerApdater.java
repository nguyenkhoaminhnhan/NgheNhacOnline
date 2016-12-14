package com.example.minhnhan.music.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.minhnhan.music.Activity.CategoryDetailActivity;
import com.example.minhnhan.music.Activity.SingerDetailActivity;
import com.example.minhnhan.music.Model.Album;
import com.example.minhnhan.music.Model.Category;
import com.example.minhnhan.music.Model.Singer;
import com.example.minhnhan.music.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

/**
 * Created by Minh Nhan on 11/29/2016.
 */

public class SingerApdater extends RecyclerView.Adapter<SingerApdater.ViewHolder> {

    private ArrayList<Singer> data;
    private Activity activity;
    private DisplayImageOptions options;

    public SingerApdater(Activity activity, ArrayList<Singer> data) {
        this.data = data;
        this.activity = activity;
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_stub)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.default_image).cacheInMemory(true)
                .cacheOnDisk(true).considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565).build();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.singer_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Singer item = data.get(position);
        holder.singerName.setText(item.name);
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(activity));
        imageLoader.displayImage(item.getImagePath(), holder.singerImage, options, null);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        public TextView singerName;
        public ImageView singerImage;

        public ViewHolder(View itemView) {
            super(itemView);
            singerImage = (ImageView)itemView.findViewById(R.id.singer_image);
            singerName = (TextView)itemView.findViewById(R.id.singer_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final int position = getAdapterPosition();

                    Intent i = new Intent(activity, SingerDetailActivity.class);
                    Singer singer = data.get(position);
                    Bundle b = new Bundle();
                    b.putLong("id", singer.id);
                    b.putString("name", singer.name);
                    b.putString("path",singer.getImagePath());
                    b.putString("detail",singer.detail);
                    b.putString("birth",singer.birthday);
                    b.putString("nation",singer.nation);
                    i.putExtras(b);
                    activity.startActivityForResult(i, 11);
                }
            });
        }
    }
    public void addMore(ArrayList<Singer> data) {
        if (this.data == null)
            this.data = data;
        else
            this.data.addAll(data);
        // yeu cau adapter refresh lai view
        notifyDataSetChanged();
    }
}
