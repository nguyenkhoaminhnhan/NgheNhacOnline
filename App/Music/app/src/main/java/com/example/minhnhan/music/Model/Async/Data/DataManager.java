package com.example.minhnhan.music.Model.Async.Data;


import com.example.minhnhan.music.Model.Album;
import com.example.minhnhan.music.Model.Category;
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

    /*-------- for search--------*/
    private ArrayList<Song> song;
    private ArrayList<Singer> singer;
    private ArrayList<Album> album;
    private ArrayList<Category> category;

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

    public ArrayList<Song> getSong() {
        return song;
    }

    public void setSong(ArrayList<Song> song) {
        this.song = song;
    }

    public ArrayList<Singer> getSinger() {
        return singer;
    }

    public void setSinger(ArrayList<Singer> singer) {
        this.singer = singer;
    }

    public ArrayList<Album> getAlbum() {
        return album;
    }

    public void setAlbum(ArrayList<Album> album) {
        this.album = album;
    }

    public ArrayList<Category> getCategory() {
        return category;
    }

    public void setCategory(ArrayList<Category> category) {
        this.category = category;
    }
}
