package com.example.minhnhan.music.Model.Page;

import com.example.minhnhan.music.Model.Category;
import com.example.minhnhan.music.Model.Singer;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Minh Nhan on 12/2/2016.
 */

public class SingerPage {
    public final String VIET_NAM = "VietNam";
    public final String AU_MY = "AuMy";
    public final String CHAU_A = "ChauA";
    public final String HOA_TAU = "HoaTau";

    private ArrayList<Singer> VietNam;
    private ArrayList<Singer> AuMy;
    private ArrayList<Singer> ChauA;
    private ArrayList<Singer> HoaTau;

    public SingerPage(JSONObject object) {
        try {
            if (object.has(VIET_NAM)) {
                String data = object.getString(VIET_NAM);
                JSONArray jsonArray = new JSONArray(data);
                ArrayList<Singer> results = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    Singer singer = new Singer(jsonArray.getJSONObject(i));
                    results.add(singer);
                }
                setVietNam(results);
            }
            if (object.has(AU_MY)) {
                String data = object.getString(AU_MY);
                JSONArray jsonArray = new JSONArray(data);
                ArrayList<Singer> results = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    Singer singer = new Singer(jsonArray.getJSONObject(i));
                    results.add(singer);
                }
                setAuMy(results);
            }
            if (object.has(CHAU_A)) {
                String data = object.getString(CHAU_A);
                JSONArray jsonArray = new JSONArray(data);
                ArrayList<Singer> results = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    Singer singer = new Singer(jsonArray.getJSONObject(i));
                    results.add(singer);
                }
                setChauA(results);
            }
            if (object.has(HOA_TAU)) {
                String data = object.getString(HOA_TAU);
                JSONArray jsonArray = new JSONArray(data);
                ArrayList<Singer> results = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    Singer singer = new Singer(jsonArray.getJSONObject(i));
                    results.add(singer);
                }
                setHoaTau(results);
            }
        } catch (Exception e) {
        }
    }

    public ArrayList<Singer> getVietNam() {
        return VietNam;
    }

    public void setVietNam(ArrayList<Singer> vietNam) {
        VietNam = vietNam;
    }

    public ArrayList<Singer> getAuMy() {
        return AuMy;
    }

    public void setAuMy(ArrayList<Singer> auMy) {
        AuMy = auMy;
    }

    public ArrayList<Singer> getChauA() {
        return ChauA;
    }

    public void setChauA(ArrayList<Singer> chauA) {
        ChauA = chauA;
    }

    public ArrayList<Singer> getHoaTau() {
        return HoaTau;
    }

    public void setHoaTau(ArrayList<Singer> hoaTau) {
        HoaTau = hoaTau;
    }
}