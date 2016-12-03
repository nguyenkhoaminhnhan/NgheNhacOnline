package com.example.minhnhan.music.Model.Page;

import com.example.minhnhan.music.Model.Category;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Minh Nhan on 12/2/2016.
 */

public class CategoryPage {
    public final String VIET_NAM = "VietNam";
    public final String AU_MY = "AuMy";
    public final String CHAU_A = "ChauA";
    public final String KHONG_LOI = "KhongLoi";

    private ArrayList<Category> VietNam;
    private ArrayList<Category> AuMy;
    private ArrayList<Category> ChauA;
    private ArrayList<Category> KhongLoi;

    public CategoryPage(JSONObject object) {
        try {
            if (object.has(VIET_NAM)) {
                String data = object.getString(VIET_NAM);
                JSONArray jsonArray = new JSONArray(data);
                ArrayList<Category> results = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    Category category = new Category(jsonArray.getJSONObject(i));
                    results.add(category);
                }
                setVietNam(results);
            }
            if (object.has(AU_MY)) {
                String data = object.getString(AU_MY);
                JSONArray jsonArray = new JSONArray(data);
                ArrayList<Category> results = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    Category category = new Category(jsonArray.getJSONObject(i));
                    results.add(category);
                }
                setAuMy(results);
            }
            if (object.has(CHAU_A)) {
                String data = object.getString(CHAU_A);
                JSONArray jsonArray = new JSONArray(data);
                ArrayList<Category> results = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    Category category = new Category(jsonArray.getJSONObject(i));
                    results.add(category);
                }
                setChauA(results);
            }
            if (object.has(KHONG_LOI)) {
                String data = object.getString(KHONG_LOI);
                JSONArray jsonArray = new JSONArray(data);
                ArrayList<Category> results = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    Category category = new Category(jsonArray.getJSONObject(i));
                    results.add(category);
                }
                setKhongLoi(results);
            }
        } catch (Exception e) {
        }
    }

    public ArrayList<Category> getVietNam() {
        return VietNam;
    }

    public void setVietNam(ArrayList<Category> vietNam) {
        VietNam = vietNam;
    }

    public ArrayList<Category> getAuMy() {
        return AuMy;
    }

    public void setAuMy(ArrayList<Category> auMy) {
        AuMy = auMy;
    }

    public ArrayList<Category> getChauA() {
        return ChauA;
    }

    public void setChauA(ArrayList<Category> chauA) {
        ChauA = chauA;
    }

    public ArrayList<Category> getKhongLoi() {
        return KhongLoi;
    }

    public void setKhongLoi(ArrayList<Category> khongLoi) {
        KhongLoi = khongLoi;
    }
}