package com.example.minhnhan.music.Utils;

/**
 * Created by Minh Nhan on 11/20/2016.
 */

public class Constants {
    // url
    public static final String DOMAIN = "http://nghenhac.apphb.com/";
    public static final String GET_SHOW_CASE = DOMAIN + "song/getshowcase";
    public static final String GET_CATEGORY = DOMAIN + "category/getcategory";
    public static final String GET_SINGER = DOMAIN + "singer/getsinger";
    public static final String GET_ALBUM = DOMAIN + "album/getalbum";
    public static final String GET_TO_PLAY = DOMAIN + "song/gettoplay?id=";
    public static final String GET_SONG_BY_ALBUM_ID = DOMAIN + "song/GetSongByAlbum?albumID=";
    public static final String GET_ALBUM_BY_CAT = DOMAIN + "Album/getAlbumByCat?cat=%s&page=%d";
    public static final String MORE_ALBUM = DOMAIN + "Album/getmorealbum?type=%d&page=%d";
    public static final String UPDATE_LISTEN = DOMAIN + "song/updateluotnghe?id=";
    public static final String GET_SINGER_VN = DOMAIN + "singer/getSingerVN?page=";
    public static final String GET_SINGER_AU_MY = DOMAIN + "singer/getSingerAuMy?page=";
    public static final String GET_SINGER_CHAU_A = DOMAIN + "singer/getSingerChauA?page=";
    public static final String GET_SINGER_HOA_TAU = DOMAIN + "singer/getSingerHoaTau?page=";
    public static final String GET_SONG_BY_CAT = DOMAIN + "Song/GetSongByCat?catid=%d&page=%d";
    public static final String GET_SONG_BY_SINGER = DOMAIN + "song/GetSongBySinger?singerid=%d&page=%d";
    public static final String GET_ALBUM_BY_SINGER = DOMAIN + "album/getAlbumBySinger?sName=%s&page=%d";
}
