package com.example.minhnhan.music.Model.Async.Data;


import com.example.minhnhan.music.Model.Album;
import com.example.minhnhan.music.Model.Page.AlbumPage;
import com.example.minhnhan.music.Model.Page.CategoryPage;
import com.example.minhnhan.music.Model.Page.HomePage;
import com.example.minhnhan.music.Model.Page.SingerPage;
import com.example.minhnhan.music.Model.Singer;
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
    private SingerPage singerPage;
    private AlbumPage albumPage;
    private ArrayList<Album> moreAlbum;
    private ArrayList<Singer> moreSinger;

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

    public SingerPage getSingerPage() {
        return singerPage;
    }

    public void setSingerPage(SingerPage singerPage) {
        this.singerPage = singerPage;
    }

    public AlbumPage getAlbumPage() {
        return albumPage;
    }

    public void setAlbumPage(AlbumPage albumPage) {
        this.albumPage = albumPage;
    }

    public ArrayList<Album> getMoreAlbum() {
        return moreAlbum;
    }

    public void setMoreAlbum(ArrayList<Album> moreAlbum) {
        this.moreAlbum = moreAlbum;
    }

    public ArrayList<Singer> getMoreSinger() {
        return moreSinger;
    }

    public void setMoreSinger(ArrayList<Singer> moreSinger) {
        this.moreSinger = moreSinger;
    }
}
