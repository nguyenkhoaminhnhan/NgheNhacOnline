package com.example.minhnhan.music.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.minhnhan.music.Activity.AlbumDetailActivity;
import com.example.minhnhan.music.Activity.CategoryDetailActivity;
import com.example.minhnhan.music.Model.Category;
import com.example.minhnhan.music.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

/**
 * Created by Minh Nhan on 11/29/2016.
 */

public class CategoryApdater extends RecyclerView.Adapter<CategoryApdater.ViewHolder> {

    private ArrayList<Category> data;
    private Activity activity;
    private DisplayImageOptions options;

    public CategoryApdater(Activity activity, ArrayList<Category> data) {
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
                .inflate(R.layout.category_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Category item = data.get(position);
        holder.albumName.setText(item.name);
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(activity));
        imageLoader.displayImage(item.getImagePath(), holder.albumImage, options, null);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView albumName;
        public ImageView albumImage;

        public ViewHolder(View itemView) {
            super(itemView);
            albumImage = (ImageView) itemView.findViewById(R.id.category_image);
            albumName = (TextView) itemView.findViewById(R.id.category_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final int position = getAdapterPosition();

                    Intent i = new Intent(activity, CategoryDetailActivity.class);
                    Category cat = data.get(position);
                    Bundle b = new Bundle();
                    b.putLong("id", cat.id);
                    b.putString("key", cat.name);
                    i.putExtras(b);
                    activity.startActivityForResult(i, 11);
                }
            });
        }
    }
}
