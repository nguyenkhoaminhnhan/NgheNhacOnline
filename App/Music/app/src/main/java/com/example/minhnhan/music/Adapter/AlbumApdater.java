package com.example.minhnhan.music.Adapter;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.minhnhan.music.Activity.HomeActivity;
import com.example.minhnhan.music.Model.Album;
import com.example.minhnhan.music.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

/**
 * Created by Minh Nhan on 11/29/2016.
 */

public class AlbumApdater extends RecyclerView.Adapter<AlbumApdater.ViewHolder> {

    private ArrayList<Album> data;
    private DisplayImageOptions options;
    private HomeActivity activity;

    public AlbumApdater(HomeActivity activity, ArrayList<Album> data) {
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
                .inflate(R.layout.album_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Album item = data.get(position);
        holder.AlbumName.setText(item.name);
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(activity));
        imageLoader.displayImage(item.getImagePath(), holder.AlbumImage, options, null);

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView AlbumName;
        public ImageView AlbumImage;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    activity.playAlbum(data.get(position).id);
                }
            });
            AlbumImage = (ImageView) itemView.findViewById(R.id.album_image);
            AlbumName = (TextView) itemView.findViewById(R.id.album_name);
        }
    }
}
