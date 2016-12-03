package com.example.minhnhan.music.Model.Async.Data;


import com.example.minhnhan.music.Model.Page.CategoryPage;
import com.example.minhnhan.music.Model.Page.HomePage;
import com.example.minhnhan.music.Model.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MinhNhan on 21/11/2016.
 * Restore all data off program from server
 */
public class DataManager {
    private static DataManager instance;

    private ArrayList<Song> songDetail;

    private HomePage homePage;
    private CategoryPage categoryPage;


    public static DataManager getInstance() {
        if (instance == null)
            instance = new DataManager();
        return instance;
    }

    private DataManager() {
    }

    /* ------------------------------------------- */

    public void setSongDetail(ArrayList<Song> nSong) {
        songDetail = nSong;
    }

    public List<Song> getSongDetail() {
        return songDetail;
    }

    /* ------------------------------------------- */

    public void setHomeDetail(HomePage nHomePage) {
        homePage = nHomePage;
    }

    public HomePage getHomeDetail() {
        return homePage;
    }

    public CategoryPage getCategoryPage() {
        return categoryPage;
    }

    public void setCategoryPage(CategoryPage categoryPage) {
        this.categoryPage = categoryPage;
    }
}
