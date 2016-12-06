package com.example.minhnhan.music.Model;

import org.json.JSONObject;

/**
 * Created by Minh Nhan on 11/16/2016.
 */

public class Album {
    public final String ID ="ID";
    public final String NAME ="Name";
    public final String IMAGE_PATH ="ImagePath";
    public final String DETAIL ="Detail";

    public long id;
    public String name;
    private String imagePath;
    public String detail;

    public Album(JSONObject object) {
        try {
            if (object.has(ID))
                id = Long.parseLong(object.getString(ID));
            if (object.has(NAME))
                name = object.getString(NAME);
            if (object.has(NAME))
                name = object.getString(NAME);
            if (object.has(IMAGE_PATH))
                setImagePath(object.getString(IMAGE_PATH));
            if (object.has(DETAIL))
                detail = object.getString(DETAIL);
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
