package com.example.minhnhan.music.Model.Page;

import com.example.minhnhan.music.Model.Album;
import com.example.minhnhan.music.Model.Singer;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Minh Nhan on 12/2/2016.
 */

public class AlbumPage {
    public final String VIET_NAM = "VietNam";
    public final String AU_MY = "AuMy";
    public final String CHAU_A = "ChauA";
    public final String KHONG_LOI = "KhongLoi";

    private ArrayList<Album> VietNam;
    private ArrayList<Album> AuMy;
    private ArrayList<Album> ChauA;
    private ArrayList<Album> KhongLoi;

    public AlbumPage(JSONObject object) {
        try {
            if (object.has(VIET_NAM)) {
                String data = object.getString(VIET_NAM);
                JSONArray jsonArray = new JSONArray(data);
                ArrayList<Album> results = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    Album album = new Album(jsonArray.getJSONObject(i));
                    results.add(album);
                }
                setVietNam(results);
            }
            if (object.has(AU_MY)) {
                String data = object.getString(AU_MY);
                JSONArray jsonArray = new JSONArray(data);
                ArrayList<Album> results = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    Album album = new Album(jsonArray.getJSONObject(i));
                    results.add(album);
                }
                setAuMy(results);
            }
            if (object.has(CHAU_A)) {
                String data = object.getString(CHAU_A);
                JSONArray jsonArray = new JSONArray(data);
                ArrayList<Album> results = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    Album album = new Album(jsonArray.getJSONObject(i));
                    results.add(album);
                }
                setChauA(results);
            }
            if (object.has(KHONG_LOI)) {
                String data = object.getString(KHONG_LOI);
                JSONArray jsonArray = new JSONArray(data);
                ArrayList<Album> results = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    Album album = new Album(jsonArray.getJSONObject(i));
                    results.add(album);
                }
                setKhongLoi(results);
            }
        } catch (Exception e) {
        }
    }

    public ArrayList<Album> getVietNam() {
        return VietNam;
    }

    public void setVietNam(ArrayList<Album> vietNam) {
        VietNam = vietNam;
    }

    public ArrayList<Album> getAuMy() {
        return AuMy;
    }

    public void setAuMy(ArrayList<Album> auMy) {
        AuMy = auMy;
    }

    public ArrayList<Album> getChauA() {
        return ChauA;
    }

    public void setChauA(ArrayList<Album> chauA) {
        ChauA = chauA;
    }

    public ArrayList<Album> getKhongLoi() {
        return KhongLoi;
    }

    public void setKhongLoi(ArrayList<Album> khongLoi) {
        KhongLoi = khongLoi;
    }
}