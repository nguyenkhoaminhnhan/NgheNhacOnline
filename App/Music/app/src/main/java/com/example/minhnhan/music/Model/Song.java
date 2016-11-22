package com.example.minhnhan.music.Model;

import org.json.JSONObject;

/**
 * Created by Minh Nhan on 11/16/2016.
 */

public class Song {

    public final String ID ="ID";
    public final String NAME ="Name";
    public final String IMAGE_PATH ="ImagePath";
    public final String ALBUM_NAME ="Album";
    public final String SINGER_NAME ="Singer";
    public final String YEAR ="Year";
    public final String FORMAT ="Format";
    public final String BITRATE ="BitRate";
    public final String TAG ="Tag";
    public final String RATING ="Rating";


    private long id;
    private String name;
    private String imagePath;
    private String album;
    private String singer;
    private String year;
    private String format;
    private int bitRate;
    private String tag;
    private float rating;

    public Song(JSONObject object) {
        try {
            if (object.has(ID))
                id = Long.parseLong(object.getString(ID));
            if (object.has(NAME))
                name = object.getString(NAME);
            if (object.has(NAME))
                name = object.getString(NAME);
            if (object.has(IMAGE_PATH))
                setImagePath(object.getString(IMAGE_PATH));
            if (object.has(ALBUM_NAME))
                album = object.getString(ALBUM_NAME);
            if (object.has(SINGER_NAME))
                singer = object.getString(SINGER_NAME);
            if (object.has(YEAR))
                year = object.getString(YEAR);
            if (object.has(FORMAT))
                format = object.getString(FORMAT);
            if (object.has(BITRATE))
                bitRate = Integer.parseInt(object.getString(BITRATE));
            if (object.has(TAG))
                tag = object.getString(TAG);
            if (object.has(RATING))
                rating = Float.parseFloat(object.getString(RATING));
        } catch (Exception e) {
        }
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
